package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.IMessageValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoou on 2017/3/23.
 */
public interface IMessageDAO {
    public List<IMessageValue> queryMessageInfoByCondition(String condition, HashMap params, int  begin, int end) throws Exception;
    public void save(IMessageValue value) throws  Exception;
}
