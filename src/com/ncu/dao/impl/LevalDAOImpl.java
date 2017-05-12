package com.ncu.dao.impl;

import com.ncu.dao.interfaces.ILevalDAO;
import com.ncu.table.engine.LevalEngine;
import com.ncu.table.ivalue.ILevalValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/5/10.
 */
@Repository("LevalDAOImpl")
public class LevalDAOImpl implements ILevalDAO {
    @Autowired
    private LevalEngine levalEngine;
    public void save(ILevalValue value) throws Exception{
        levalEngine.save(value);
    }
    public List<ILevalValue> queryLevalByCondition(String condition, HashMap params, int begin, int end) throws Exception{
        return levalEngine.queryByCondition(condition,params,begin,end);
    }
}
