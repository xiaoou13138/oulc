package com.ncu.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncu.dao.interfaces.IWebInfoDAO;
import com.ncu.table.engine.WebInfoEngine;
import com.ncu.table.ivalue.IWebInfoValue;

import java.util.HashMap;
import java.util.List;

@Repository("WebInfoDAOImpl")
public class WebInfoDAOImpl implements IWebInfoDAO{
	@Autowired
	WebInfoEngine webInfoEngine;
	@Override
	public void save(IWebInfoValue value) throws Exception{
		webInfoEngine.save(value);
	}

	@Override
	public List<IWebInfoValue> queryWebInfoByCondition(String condition, HashMap params, int begin, int end) throws Exception{
		return webInfoEngine.queryByCondition(condition,params,begin,end);

	}
}
