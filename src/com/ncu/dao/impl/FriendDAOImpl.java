package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IFriendDAO;
import com.ncu.table.engine.FriendEngine;
import com.ncu.table.ivalue.IFriendValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/27.
 */
@Repository("FriendDAOImpl")
public class FriendDAOImpl implements IFriendDAO {
    @Autowired
    FriendEngine friendEngine;
    @Override
    public List<IFriendValue> queryInfoByCodition(String condition, HashMap params, int begin, int end) throws Exception {
        return friendEngine.queryByCondition(condition,params,begin,end);
    }

    @Override
    public void save(IFriendValue value) throws Exception {
        friendEngine.save(value);
    }
}
