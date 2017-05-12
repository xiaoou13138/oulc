package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IAuditDAO;
import com.ncu.table.engine.AuditEngine;
import com.ncu.table.ivalue.IAuditValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Repository("AuditDAOImpl")
public class AuditDAOImpl implements IAuditDAO {
    @Autowired
    private AuditEngine auditEngine;
    public void save(IAuditValue value) throws Exception{
        auditEngine.save(value);
    }
    public List<IAuditValue> queryAuditByCondition(String condition, HashMap params, int begin, int end)throws Exception{
        return auditEngine.queryByCondition(condition,params,begin,end);
    }
    public long queryAuditCountByCondition(String condition,HashMap params) throws Exception{
        return auditEngine.queryCountByCondition(condition,params);
    }

}
