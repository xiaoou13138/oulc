package com.ncu.dao.impl;

import com.ncu.dao.interfaces.IOrgDefDAO;
import com.ncu.table.engine.OrgDefEngine;
import com.ncu.table.ivalue.IOrgDefValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Repository("OrgDefDAOImpl")
public class OrgDefDAOImpl implements IOrgDefDAO{
    @Autowired
    private OrgDefEngine orgDefEngine;
    public void save(IOrgDefValue value)throws Exception{
        orgDefEngine.save(value);
    }
    public List<IOrgDefValue> queryOrgDefByCondition(String condition, HashMap params, int begin, int end)throws Exception{
        return orgDefEngine.queryByCondition(condition,params,begin,end);
    }
    public long queryOrgDefCountByCondition(String condition, HashMap params)throws Exception{
        return orgDefEngine.queryCountByCondition(condition,params);
    }
}
