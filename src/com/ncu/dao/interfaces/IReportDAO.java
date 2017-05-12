package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IReportValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/5/12.
 */
public interface IReportDAO {
    public void save(IReportValue value)throws Exception;
    public List<IReportValue> queryReportByCondition(String condition, HashMap params, int begin, int end) throws Exception;
    public long queryReportCountByCondition(String condition, HashMap params)throws Exception;
}
