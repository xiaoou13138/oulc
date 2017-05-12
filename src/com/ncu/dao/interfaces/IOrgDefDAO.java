package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IOrgDefValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
public interface IOrgDefDAO {
    public void save(IOrgDefValue value)throws Exception;
    public List<IOrgDefValue> queryOrgDefByCondition(String condition, HashMap params, int begin, int end)throws Exception;
    public long queryOrgDefCountByCondition(String condition, HashMap params)throws Exception;
}
