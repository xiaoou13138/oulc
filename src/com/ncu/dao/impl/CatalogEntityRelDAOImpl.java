package com.ncu.dao.impl;

import com.ncu.dao.interfaces.ICatalogEntityRelDAO;
import com.ncu.table.engine.CatalogEntityRelEngine;
import com.ncu.table.ivalue.ICatalogEntityRelValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Repository("CatalogEntityRelDAOImpl")
public class CatalogEntityRelDAOImpl implements ICatalogEntityRelDAO{
    @Autowired
    private CatalogEntityRelEngine catalogEntityRelEngine;

    public void save(ICatalogEntityRelValue value)throws Exception{
        catalogEntityRelEngine.save(value);
    }
    public List<ICatalogEntityRelValue> queryCatalogEntityRelByCondition(String condition, HashMap params, int begin, int end)throws Exception{
        return catalogEntityRelEngine.queryByCondition(condition,params,begin,end);
    }
}
