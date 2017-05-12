package com.ncu.service.impl;


import com.ncu.util.SQLCon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.dao.impl.WebInfoDAOImpl;
import com.ncu.service.interfaces.IWebInfoSV;
import com.ncu.table.ivalue.IWebInfoValue;
import java.util.List;
import java.util.HashMap;

@Service("WebInfoSVImpl")
public class WebInfoSVImpl implements IWebInfoSV{
	@Autowired
	private WebInfoDAOImpl dao;
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(IWebInfoValue value) throws Exception {
		dao.save(value);
	}
	/**
	 * 查询网页信息
	 * @param webId
	 * @throws Exception
	 */
	public IWebInfoValue queryWebInfoByWebId(long webId)throws Exception{
		StringBuilder condition = new StringBuilder();
		HashMap params = new HashMap();
		SQLCon.connectSQL(IWebInfoValue.S_Id,webId,condition,params,false);
		SQLCon.connectSQL(IWebInfoValue.S_DelFlag,1L,condition,params,false);
		List<IWebInfoValue> list = dao.queryWebInfoByCondition(condition.toString(),params,-1,-1);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 设置评级
	 * @param webId
	 * @param leval
	 * @throws Exception
	 */
	public void saveLeval(long webId,long leval)throws Exception{
		IWebInfoValue webInfoValue = queryWebInfoByWebId(webId);
		if(webInfoValue == null){
			throw new Exception("网页不存在");
		}
	}
	

}
