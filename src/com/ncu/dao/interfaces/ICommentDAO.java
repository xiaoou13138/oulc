package com.ncu.dao.interfaces;

import com.ncu.table.ivalue.ICommentValue;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/29.
 */
public interface ICommentDAO {
    public void save(ICommentValue value) throws Exception;
    public List<ICommentValue> queryCommentInfoByCondition(String condition, HashMap params,int begin,int end)  throws Exception;

}
