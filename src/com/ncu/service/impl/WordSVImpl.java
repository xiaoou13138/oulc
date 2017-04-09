package com.ncu.service.impl;

import java.beans.Transient;
import java.util.HashMap;
import java.util.List;

import com.ncu.util.SQLCon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncu.dao.impl.WordDAOImol;
import com.ncu.service.interfaces.IWordSV;
import com.ncu.table.ivalue.IWordValue;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("WordSVImpl")
public class WordSVImpl implements IWordSV{
	@Autowired
	private WordDAOImol dao;
	
	public List<IWordValue> queryWordInfoAll() throws Exception {
		StringBuilder condition = new StringBuilder();
		HashMap params = new HashMap();
		return  dao.queryWordInfoByCondition(condition.toString(), params, -1, -1);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(IWordValue value) throws Exception{
		dao.save(value);
	}

	@Override
	public long getWordIdByWord(String word) throws Exception {
		StringBuilder condition = new StringBuilder();
		HashMap params = new HashMap();
		SQLCon.connectSQL(IWordValue.S_Word,word,condition,params,false);
		List <IWordValue> list = dao.queryWordInfoByCondition(condition.toString(),params,-1,-1);
		if(list != null && list.size()>0){
			return list.get(0).getWordId();
		}
		return 0L;
	}
}
