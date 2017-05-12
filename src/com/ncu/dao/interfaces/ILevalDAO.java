package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.ILevalValue;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/5/10.
 */

public interface ILevalDAO {
    public void save(ILevalValue value) throws Exception;
    public List<ILevalValue> queryLevalByCondition(String condition, HashMap params, int begin, int end) throws Exception;
}
