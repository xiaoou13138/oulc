package com.ncu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncu.dao.impl.WordLinkDAOImpl;
import com.ncu.service.interfaces.IWordLinkSV;
import com.ncu.table.ivalue.IWordLinkValue;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("WordLinkSVImpl")
public class WordLinkSVImpl implements IWordLinkSV{
	@Autowired
	private WordLinkDAOImpl dao;
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(IWordLinkValue value) throws Exception {
		dao.save(value);
	}

	@Override
	public List<IWordLinkValue> queryLinkInfoByWordIds(String[] wordIds) throws Exception {
		return null;
	}
}
