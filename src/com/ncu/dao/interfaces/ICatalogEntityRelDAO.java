package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.ICatalogEntityRelValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
public interface ICatalogEntityRelDAO {
    public void save(ICatalogEntityRelValue value)throws Exception;
    public List<ICatalogEntityRelValue> queryCatalogEntityRelByCondition(String condition, HashMap params,int begin,int end)throws Exception;
}
