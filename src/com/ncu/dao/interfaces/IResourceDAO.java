package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IResourceValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/20.
 */
public interface IResourceDAO {
    public void save(IResourceValue value)throws Exception;
    public List<IResourceValue> queryResourceByCondition(String condition, HashMap params,int begin,int end)throws Exception;
    public long queryResourceCountByCondition(String condition,HashMap params)throws Exception;
}
