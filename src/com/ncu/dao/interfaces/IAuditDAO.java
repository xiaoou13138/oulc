package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IAuditValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
public interface IAuditDAO {
    public void save(IAuditValue value) throws Exception;
    public List<IAuditValue> queryAuditByCondition(String condition, HashMap params,int begin,int end)throws Exception;
    public long queryAuditCountByCondition(String condition,HashMap params) throws Exception;
}
