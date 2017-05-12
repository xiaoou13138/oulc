package com.ncu.dao.impl;

import com.ncu.dao.interfaces.ICatalogDAO;
import com.ncu.table.engine.CatalogEngine;
import com.ncu.table.ivalue.ICatalogValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Repository("CatalogDAOImpl")
public class CatalogDAOImpl implements ICatalogDAO{
    @Autowired
    private CatalogEngine catalogEngine;

    public void save(ICatalogValue value)throws Exception{
        catalogEngine.save(value);
    }
    public List<ICatalogValue> queryCatalogByCondition(String condition, HashMap params, int begin, int end)throws Exception{
        return catalogEngine.queryByCondition(condition,params,begin,end);
    }
}
