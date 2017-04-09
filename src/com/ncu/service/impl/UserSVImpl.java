package com.ncu.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.table.bean.ParamsDefine;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.infinispan.commons.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.dao.impl.UserDAOImpl;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.SQLCon;

import javax.servlet.http.Cookie;

@Service("UserSVImpl")
public class UserSVImpl implements IUserSV {
	@Autowired
	private UserDAOImpl dao;
	@Autowired
	@Qualifier("CommonDAOImpl")
	private ICommonDAO commonDAO;
	
    /**
     * 保存用户的信息
     * @param value 用户信息的数据容器
     */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(IUserValue value) throws Exception{
		if(value != null){
			dao.save(value);
		}
	}
	
	/**
	 * 根据账号和密码校验用户的信息
	 * @param code 用户账号
	 * @return 通过校验:true
	 * @throws Exception
	 */
	public HashMap  checkUserInfo(String code,String password) throws Exception {
		HashMap rtnMap = new HashMap();
		rtnMap.put("result",false);
		if(StringUtils.isNotBlank(code) &&StringUtils.isNotBlank(password)){
			IUserValue value = getUserInfoByCodeAndPassword(code,password);
			if(value != null){
				rtnMap.put("result",true);
				rtnMap.put("userId",value.getUserId());
				rtnMap.put("userName",value.getName());
			}
		}
		return rtnMap;
	}
	/**
	 * 根据用户的账号查找用户的信息
	 * @param code 用户账号
	 * @return 用户信息容器
	 * @throws Exception
	 */
	public IUserValue getUserInfoByCode(String code) throws Exception {
		if(StringUtils.isNotBlank(code)){
			StringBuilder condition = new StringBuilder("");
			HashMap<String,String> params = new HashMap<String, String>();
			SQLCon.connectSQL(IUserValue.S_Code, code, condition, params, false);
			List<IUserValue> iUserValues = dao.queryUserInfoByCondition(condition.toString(), params);
			if(iUserValues!=null){
				if(iUserValues.size()==1){
					return iUserValues.get(0);
				}else if(iUserValues.size()>1){
					throw new Exception("该账号异常");
				}
			}
		}
		return null;
	}

	/**
	 * 根据用户code判是否已经存在了这样的账号
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public boolean checkHasEqualCode(String code) throws Exception {
		if(StringUtils.isNotBlank(code)){
			IUserValue value = getUserInfoByCode(code);
			if(value == null){
				return false;
			}
		}
		return true;
	}

	/**
	 * 根据用户的账号和密码查询用户的信息
	 * @param code
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public IUserValue getUserInfoByCodeAndPassword(String code, String password) throws Exception {
		if(StringUtils.isNotBlank(code) && StringUtils.isNotBlank(password)){
			StringBuilder condition = new StringBuilder();
			HashMap params = new HashMap();
			SQLCon.connectSQL(IUserValue.S_Code,code,condition,params,false);
			SQLCon.connectSQL(IUserValue.S_Password,password,condition,params,false);
			List<IUserValue> list = dao.queryUserInfoByCondition(condition.toString(),params);
			if(list !=null && list.size()==1){
				return list.get(0);
			}
			return null;
		}
		return null;
	}

	/**
	 * 根据用户id数组查询用户的信息
	 * @param userIdList
	 * @return
	 * @throws Exception
	 */
	public List<IUserValue> queryUserInfoByUserIds(ArrayList userIdList) throws Exception {
		if(userIdList != null && userIdList.size()>0){
			ArrayList<ParamsDefine> list= new ArrayList();
			String sql = "from UserBean a where a.userId in(:userIds)";
			ParamsDefine paramsDefine = new ParamsDefine();
			paramsDefine.setIsList(true);
			paramsDefine.setColName("userIds");
			paramsDefine.setParamListVal(userIdList);
			list.add(paramsDefine);
			List <IUserValue> rtnValue = commonDAO.commonQuery(sql,list.toArray(new ParamsDefine[0]));
			return rtnValue;
		}
		return null;

	}

	/**
	 * 根据用户的ID查用户的信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public IUserValue queryUserInfoByUserId(long userId) throws Exception {
		StringBuilder condition = new StringBuilder();
		HashMap params = new HashMap();
		SQLCon.connectSQL(IUserValue.S_UserId,userId,condition,params,false);
		List<IUserValue> list = dao.queryUserInfoByCondition(condition.toString(),params,-1,-1);
		if( list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据cookie校验用户然后返回信息 如果用户验证通过 就返回userId和userName
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
	public HashMap checkUserInfoByCookie(Cookie[] cookies) throws Exception {
		HashMap map = new HashMap();
		String rtnUserId = "";
		String rtnName = "";
		String cookieUserId = "";
		String cookiePassword = "";
		if(cookies != null && cookies.length>0){
			for(int i = 0;i<cookies.length;i++){
				String key = URLDecoder.decode(cookies[i].getName(), "UTF-8");
				String value = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
				if("codeVal".equals(key)){
					cookieUserId = value;
				}
				if("passwordVal".equals(key)){
					cookiePassword = value;
				}
			}
			HashMap checkMap = checkUserInfo(cookieUserId,cookiePassword);
			if((boolean)checkMap.get("result")){
				rtnUserId = String.valueOf(checkMap.get("userId"));
				rtnName = String.valueOf(checkMap.get("userName"));
			}
		}
		map.put("userId",rtnUserId);
		map.put("userName",rtnName);
		return map;
	}

	/**
	 * 根据用户的主键查询修改用户信息页面的信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap getEditViewInitData(long userId) throws Exception{
		HashMap rtnMap = new HashMap();
		if(userId !=0){
			IUserValue value = queryUserInfoByUserId(userId);
			if(value != null){
				//需要用户的手机号码   真实名称  性别
				rtnMap.put("phoneNum",value.getPhone());
				rtnMap.put("realName",value.getRealName());
				rtnMap.put("sex",value.getSex());
			}else{
				rtnMap.put("phoneNum","");
				rtnMap.put("realName","");
				rtnMap.put("sex","");
			}
		}
		return rtnMap;
	}
}
