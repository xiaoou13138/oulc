package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IApplyValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
public interface IApplyDAO {
    public void save(IApplyValue value) throws Exception;
    public List<IApplyValue> queryApplyByCondition(String condition, HashMap params, int begin, int end) throws Exception;
    public long queryApplyCountByCondition(String condition, HashMap params)throws Exception;
}
