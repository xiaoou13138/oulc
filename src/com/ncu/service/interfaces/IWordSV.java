package com.ncu.service.interfaces;

import java.util.List;

import com.ncu.table.ivalue.IWordValue;

public interface IWordSV {
	public List<IWordValue> queryWordInfoAll() throws Exception;
	
	public void save(IWordValue value) throws Exception;

	public long getWordIdByWord(String word) throws Exception;
}
