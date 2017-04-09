package com.ncu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncu.dao.interfaces.IWordLinkDAO;
import com.ncu.table.engine.WordLinkEngine;
import com.ncu.table.ivalue.IWordLinkValue;
@Repository
public class WordLinkDAOImpl implements IWordLinkDAO{
	@Autowired
	WordLinkEngine wordLinkEngine;
	public void save(IWordLinkValue value) throws Exception {
		wordLinkEngine.save(value);
	}

}
