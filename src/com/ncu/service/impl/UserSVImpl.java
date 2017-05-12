package com.ncu.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ncu.cache.StaticDataCache;
import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.service.interfaces.IAuditSV;
import com.ncu.service.interfaces.IReportSV;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.util.TimeUtil;
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

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

@Service("UserSVImpl")
public class UserSVImpl implements IUserSV {
	@Autowired
	private UserDAOImpl dao;
	@Autowired
	@Qualifier("CommonDAOImpl")
	private ICommonDAO commonDAO;

	@Autowired
	private StaticDataCache staticDataCache;

	@Resource(name="AuditSVImpl")
	private IAuditSV auditSV;

	@Resource(name="ReportSVImpl")
	private IReportSV reportSV;
	
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
				rtnMap.put("userType",value.getUserType());
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
		long rtnUserId = 0;
		String rtnName = "";
		String cookieUserId = "";
		String cookiePassword = "";
		String userType = "";
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
				rtnUserId = (long)checkMap.get("userId");
				rtnName = String.valueOf(checkMap.get("userName"));
				userType = String.valueOf(checkMap.get("userType"));
			}
		}
		map.put("userId",rtnUserId);
		map.put("userName",rtnName);
		map.put("userType",userType);
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
		IUserValue value = queryUserInfoByUserId(userId);
		if(value == null){
			throw new Exception("用户不存在");
		}
		if(value.getRealName() != null){
			rtnMap.put("realName",value.getRealName());
		}
		if(value.getPhone() != null){
			rtnMap.put("phoneNum",value.getPhone());
		}
		if(value.getSex() != null){
			rtnMap.put("sex",value.getSex());
		}
		if(value.getRealName() != null){
			rtnMap.put("description",value.getDescription());
		}
		if(value.getCreateDate() != null){
			rtnMap.put("createDate", TimeUtil.formatTimeWithChinese(value.getCreateDate()));
		}
		return rtnMap;
	}

	/**
	 * 模糊查询用户信息
	 * @param name 用户名称
	 * @return
	 * @throws Exception
	 */
	public List<IUserValue> queryUserInfo(String name)throws Exception{
		StringBuilder condition = new StringBuilder();
		HashMap params = new HashMap();
		SQLCon.connectSQL(IUserValue.S_Name,name,condition,params,true);
		return dao.queryUserInfoByCondition(condition.toString(),params,-1,-1);
	}
	/**
	 * 查询用户名称
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String queryUserNameByUserId(long userId)throws Exception{
		IUserValue userValue = queryUserInfoByUserId(userId);
		if(userValue != null){
			return userValue.getName();
		}
		return "";
	}

	/**
	 * 修改用户的类型
	 * @param userId
	 * @param userType
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void changeUserType(long userId,String userType) throws Exception{
		IUserValue userValue = queryUserInfoByUserId(userId);
		if(userValue == null){
			throw new Exception("用户不存在");
		}
		userValue.setUserType(userType);
		dao.save(userValue);
	}

	/**
	 * 修改用户的信息
	 * @param viewData
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUserInfo(JSONObject viewData,long userId)throws Exception{
		IUserValue userValue = queryUserInfoByUserId(userId);
		if(userValue == null){
			throw new Exception("用户不存在");
		}
		//手机号码 真实名称 性别
		if(viewData.containsKey("phoneNum")){
			userValue.setPhone(viewData.getString("phoneNum"));
		}
		if(viewData.containsKey("sex")){
			userValue.setSex(viewData.getString("sex"));
		}
		if(viewData.containsKey("realName")){
			userValue.setRealName(viewData.getString("realName"));
		}
		if(viewData.containsKey("description")){
			userValue.setDescription(viewData.getString("description"));
		}
		dao.save(userValue);
	}
	/**
	 * 查询公共账号
	 * @param searchContent
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public HashMap queryUserInfoByConditionForController(String searchContent,int begin,int end)throws Exception{
		HashMap rtnMap = new HashMap();
		List<IUserValue> userValueList = queryUserInfoByCondition(searchContent,begin,end);
		rtnMap.put("count",queryUserInfoCountByCondition(searchContent));
		if(userValueList != null &&  userValueList.size()>0){
			ArrayList rtnList = new ArrayList();
			int length = userValueList.size();
			for(int i =0;i<length;i++){
				IUserValue userValue = userValueList.get(i);
				HashMap map = new HashMap();
				map.put("userName",userValue.getName());
				map.put("userCode",userValue.getCode());
				map.put("userId",userValue.getUserId());
				if(userValue.getDescription() != null){
					map.put("userDsc",userValue.getDescription());
				}
				map.put("accountType",staticDataCache.getCodeValueByCode(userValue.getUserType()));

				//查询账号被举报过多少次  审核通过的有多少次
				HashMap reportMap = reportSV.queryReportInfoByUserId(userValue.getUserId());
				map.put("report",reportMap.get("reportCount")+"/"+reportMap.get("passReportCount"));

				rtnList.add(map);
			}
			rtnMap.put("userList",rtnList);
		}
		return rtnMap;
	}

	/**
	 * 查询公共账号
	 * @param searchContent
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<IUserValue> queryUserInfoByCondition(String searchContent,int begin,int end)throws Exception{
		if(StringUtils.isNotBlank(searchContent)){
			String sql = " from UserBean a where a.name like :searchContent or a.code like :searchContent or a.description like:searchContent";
			ParamsDefine paramsDefine = new ParamsDefine();
			paramsDefine.setIsList(false);
			paramsDefine.setColName("searchContent");
			paramsDefine.setParamVal('%'+searchContent+'%');
			ParamsDefine[] paramsDefines = {paramsDefine};
			return commonDAO.commonQuery(sql,paramsDefines,begin,end);
		}else{
			String sql = " from UserBean ";
			return commonDAO.commonQuery(sql,null,begin,end);
		}


	}
	public long queryUserInfoCountByCondition(String searchContent)throws Exception{
		if(StringUtils.isNotBlank(searchContent)){
			String sql = " from UserBean a where a.name like :searchContent or a.code like :searchContent or a.description like:searchContent";
			ParamsDefine paramsDefine = new ParamsDefine();
			paramsDefine.setIsList(false);
			paramsDefine.setColName("searchContent");
			paramsDefine.setParamVal('%'+searchContent+'%');
			ParamsDefine[] paramsDefines = {paramsDefine};
			return commonDAO.getCount(sql,paramsDefines);
		}else{
			String sql = " from UserBean ";
			return commonDAO.getCount(sql,null);
		}
	}

	/**
	 * 修改密码
	 * @param oldPassword
	 * @param newPassword
	 * @param userId
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePasswordByOldAndNewPassword(String oldPassword,String newPassword,long userId)throws Exception{
		IUserValue userValue = queryUserInfoByUserId(userId);
		if(userValue ==  null){
			throw new Exception("用户不存在");
		}
		String password = userValue.getPassword();
		if(password.equals(oldPassword)){
			userValue.setPassword(newPassword);
		}
		dao.save(userValue);
	}
}
