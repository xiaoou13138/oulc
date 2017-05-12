package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IReportDAO;
import com.ncu.table.engine.ReportEngine;
import com.ncu.table.ivalue.IReportValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/5/12.
 */
@Repository("ReportDAOImpl")
public class ReportDAOImpl implements IReportDAO{
    @Autowired
    private ReportEngine reportEngine;
    public void save(IReportValue value)throws Exception{
        reportEngine.save(value);
    }
    public List<IReportValue> queryReportByCondition(String condition, HashMap params, int begin, int end) throws Exception{
        return reportEngine.queryByCondition(condition,params,begin,end);
    }
    public long queryReportCountByCondition(String condition, HashMap params)throws Exception{
        return reportEngine.queryCountByCondition(condition,params);
    }
}
