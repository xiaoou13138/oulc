package com.ncu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncu.dao.interfaces.IWordDAO;
import com.ncu.table.engine.WordEngine;
import com.ncu.table.ivalue.IWordValue;

@Repository
public class WordDAOImol implements IWordDAO{
	@Autowired
	WordEngine wordEngine;
	public List<IWordValue> queryWordInfoByCondition(String condition, HashMap params,
			int beginPage, int endPage) throws Exception {
		return (List<IWordValue>) wordEngine.queryByCondition(condition, params, beginPage, endPage);
	}
	
	public void save(IWordValue value)  throws Exception{
		wordEngine.save(value);
	}
	

}
