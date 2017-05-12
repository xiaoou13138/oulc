package com.ncu.service.impl;

import com.ncu.dao.interfaces.IOrgDefDAO;
import com.ncu.service.interfaces.IApplySV;
import com.ncu.service.interfaces.IOrgUserRelSV;
import com.ncu.service.interfaces.IOrgDefSV;
import com.ncu.table.bean.OrgDefBean;
import com.ncu.table.ivalue.IApplyValue;
import com.ncu.table.ivalue.IOrgDefValue;
import com.ncu.table.ivalue.IOrgUserRelValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Service("OrgDefSVImpl")
public class OrgDefSVImpl implements IOrgDefSV {
    @Resource(name="OrgDefDAOImpl")
    private IOrgDefDAO orgDefDAO;
    @Resource(name="OrgUserRelSVImpl")
    private IOrgUserRelSV orgUserRelSV;
    @Resource(name="ApplySVImpl")
    private IApplySV applySV;

    /**
     * 查询组织的信息
     * @param userId 用户主键
     * @return
     */
    public IOrgDefValue queryOrgDefByUserId(long userId)throws Exception{
        IOrgUserRelValue orgUserRelValue = orgUserRelSV.queryOrgUserRelByUserId(userId);
        if(orgUserRelValue != null){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IOrgDefValue.S_OrgId,orgUserRelValue.getOrgId(),condition,params,false);
            SQLCon.connectSQL(IOrgDefValue.S_DelFlag,1L,condition,params,false);
            List<IOrgDefValue> list = orgDefDAO.queryOrgDefByCondition(condition.toString(),params,-1,-1);
            if ( list != null && list.size() > 0){
                return list.get(0);
            }
        }
        return null;
    }

    /**
     * 保存组织的信息
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public long saveOrgInfo(long applyId)throws Exception{
        //先查询申请时的数据
        IApplyValue applyValue = applySV.queryApplyById(applyId);
        if(applyValue == null){
            throw new Exception("认证材料不存在");
        }
        IOrgDefValue value = new OrgDefBean();
        value.setOrgName(applyValue.getOrgName());
        value.setOrgDesc(applyValue.getOrgDsc());
        value.setCity(applyValue.getCity());
        value.setDetailAddress(applyValue.getDetailAddress());
        value.setDelFlag(1L);
        value.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        orgDefDAO.save(value);
        return value.getOrgId();
    }

    /**
     * 查询组织的信息
     * @param orgId
     * @return
     * @throws Exception
     */
    public IOrgDefValue queryOrgDefByOrgId(long orgId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IOrgDefValue.S_OrgId,orgId,condition,params,false);
        SQLCon.connectSQL(IOrgDefValue.S_DelFlag,1L,condition,params,false);
        List<IOrgDefValue> list = orgDefDAO.queryOrgDefByCondition(condition.toString(),params,-1,-1);
        if ( list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
