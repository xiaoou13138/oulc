package com.ncu.dao.impl;

import com.ncu.dao.interfaces.ICommentDAO;
import com.ncu.table.engine.CommentEngine;
import com.ncu.table.ivalue.ICommentValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/29.
 */
@Repository("CommentDAOImpl")
public class CommentDAOImpl implements ICommentDAO {
    @Autowired
    CommentEngine commentEngine;
    @Override
    public List<ICommentValue> queryCommentInfoByCondition(String condition, HashMap params, int begin, int end) throws Exception {
        return commentEngine.queryByCondition(condition,params,begin,end);
    }

    @Override
    public void save(ICommentValue value) throws Exception {
        commentEngine.save(value);
    }
}
