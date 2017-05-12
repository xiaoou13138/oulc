package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.ICatalogValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
public interface ICatalogDAO {
    public void save(ICatalogValue value)throws Exception;
    public List<ICatalogValue> queryCatalogByCondition(String condition, HashMap params,int begin,int end)throws Exception;
}
