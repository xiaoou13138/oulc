package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IOrgUserRelDAO;
import com.ncu.table.engine.OrgUserRelEngine;
import com.ncu.table.ivalue.IOrgUserRelValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Repository("OrgUserRelDAOImpl")
public class OrgUserRelDAOImpl implements IOrgUserRelDAO {
    @Autowired
    private OrgUserRelEngine orgUserRelEngine;
    public void save(IOrgUserRelValue value)throws Exception{
        orgUserRelEngine.save(value);
    }
    public List<IOrgUserRelValue> queryOrgUserRelByCondition(String condition, HashMap params, int begin, int end)throws Exception{
        return orgUserRelEngine.queryByCondition(condition, params,begin,end);
    }
    public long queryOrgUserRelCountByCondition(String condition, HashMap params)throws Exception{
        return orgUserRelEngine.queryCountByCondition(condition,params);
    }
}
