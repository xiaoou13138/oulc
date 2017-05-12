package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IResourceDAO;
import com.ncu.table.engine.ResourceEngine;
import com.ncu.table.ivalue.IResourceValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/20.
 */
@Repository("ResourceDAOImpl")
public class ResourceDAOImpl implements IResourceDAO {
    @Autowired
    private ResourceEngine resourceEngine;

    public void save(IResourceValue value)throws Exception{
        resourceEngine.save(value);
    }
    public List<IResourceValue> queryResourceByCondition(String condition, HashMap params, int begin, int end)throws Exception{
        return resourceEngine.queryByCondition(condition,params,begin,end);
    }
    public long queryResourceCountByCondition(String condition,HashMap params)throws Exception{
        return resourceEngine.queryCountByCondition(condition,params);
    }
}
