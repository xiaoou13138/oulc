package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IApplyDAO;
import com.ncu.table.engine.ApplyEngine;
import com.ncu.table.ivalue.IApplyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Repository("ApplyDAOImpl")
public class ApplyDAOImpl implements IApplyDAO {
    @Autowired
    ApplyEngine applyEngine;
    public void save(IApplyValue value) throws Exception{
        applyEngine.save(value);
    }
    public List<IApplyValue> queryApplyByCondition(String condition, HashMap params, int begin, int end) throws Exception{
        return applyEngine.queryByCondition(condition,params,begin,end);
    }
    public long queryApplyCountByCondition(String condition, HashMap params)throws Exception{
        return  applyEngine.queryCountByCondition(condition,params);
    }
}
