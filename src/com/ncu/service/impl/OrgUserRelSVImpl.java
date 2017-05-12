package com.ncu.service.impl;

import com.ncu.dao.interfaces.IOrgUserRelDAO;
import com.ncu.service.interfaces.IOrgUserRelSV;
import com.ncu.table.bean.OrgUserRelBean;
import com.ncu.table.ivalue.IOrgUserRelValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Service("OrgUserRelSVImpl")
public class OrgUserRelSVImpl implements IOrgUserRelSV {
    @Resource(name="OrgUserRelDAOImpl")
    private IOrgUserRelDAO orgUserRelDAO;

    /**
     * 查询组织和用户的关系
     * @param userId 用户的主键
     * @return
     * @throws Exception
     */
    public IOrgUserRelValue queryOrgUserRelByUserId(long userId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IOrgUserRelValue.S_UserId,userId,condition,params,false);
        List<IOrgUserRelValue> list = orgUserRelDAO.queryOrgUserRelByCondition(condition.toString(),params,-1,-1);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 保存用户和组织的关系
     * @param orgId
     * @param userId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrgUserRel(long orgId,long userId)throws Exception{
        IOrgUserRelValue value = new OrgUserRelBean();
        value.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        value.setOrgId(orgId);
        value.setUserId(userId);
        orgUserRelDAO.save(value);
    }
}
