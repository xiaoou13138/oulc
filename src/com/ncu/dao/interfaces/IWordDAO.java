package com.ncu.dao.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ncu.table.ivalue.IWordValue;

public interface IWordDAO {
	public List<IWordValue> queryWordInfoByCondition(String condition,HashMap map,int begin,int end) throws Exception;
	
	public void save(IWordValue value) throws Exception;

}
