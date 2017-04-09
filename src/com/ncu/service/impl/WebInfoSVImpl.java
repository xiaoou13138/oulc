package com.ncu.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.dao.impl.WebInfoDAOImpl;
import com.ncu.service.interfaces.IWebInfoSV;
import com.ncu.table.ivalue.IWebInfoValue;
@Service("WebInfoSVImpl")
public class WebInfoSVImpl implements IWebInfoSV{
	@Autowired
	private WebInfoDAOImpl dao;
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(IWebInfoValue value) throws Exception {
		dao.save(value);
	}
	

}
