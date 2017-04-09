package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IWebInfoValue;
import org.infinispan.commons.hash.Hash;

import java.util.HashMap;
import java.util.List;
public interface IWebInfoDAO {
	public void save(IWebInfoValue value) throws Exception;

	public List<IWebInfoValue> queryWebInfoByCondition(String condition, HashMap params,int begin,int end) throws Exception;

}
