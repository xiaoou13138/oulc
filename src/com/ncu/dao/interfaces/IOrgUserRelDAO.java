package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IOrgUserRelValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
public interface IOrgUserRelDAO {
    public void save(IOrgUserRelValue value)throws Exception;
    public List<IOrgUserRelValue>  queryOrgUserRelByCondition(String condition, HashMap params, int begin, int end)throws Exception;
    public long queryOrgUserRelCountByCondition(String condition, HashMap params)throws Exception;
}
