package com.ncu.service.impl;

import com.ncu.cache.StaticDataCache;
import com.ncu.dao.interfaces.IApplyDAO;
import com.ncu.service.interfaces.IApplySV;
import com.ncu.service.interfaces.IAuditSV;
import com.ncu.table.bean.ApplyBean;
import com.ncu.table.ivalue.IApplyValue;
import com.ncu.table.ivalue.IStaticDataValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Service("ApplySVImpl")
public class ApplySVImpl implements IApplySV {
    @Resource(name="ApplyDAOImpl")
    private IApplyDAO applyDAO;

    @Resource(name="AuditSVImpl")
    private IAuditSV auditSV;

    @Autowired
    private StaticDataCache staticDataCache;
    /**
     * 保存认证信息
     * @param jsonObject 页面传进来的json串
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveApplyInfoByJSONObject(JSONObject jsonObject,long userId)throws Exception{
        IApplyValue value = new ApplyBean();
        value.setOrgName(jsonObject.getString("orgName"));
        value.setOrgType(jsonObject.getLong("orgType"));
        value.setCity(jsonObject.getString("cityAddress"));
        if(jsonObject.containsKey("detailAddress")){
            value.setDetailAddress(jsonObject.getString("detailAddress"));
        }
        if(jsonObject.containsKey("orgDsc")){
            value.setOrgDsc(jsonObject.getString("orgDsc"));
        }
        if(jsonObject.containsKey("certificationDsc")){
            value.setCertificationDsc(jsonObject.getString("certificationDsc"));
        }
        value.setApplyUserName(jsonObject.getString("applyUserName"));
        value.setApplyUserCertCode(jsonObject.getString("certCode"));
        value.setApplyUserPhone(jsonObject.getString("applyUserPhoneNum"));
        if(jsonObject.containsKey("mail")){
            value.setApplyUserMail(jsonObject.getString("mail"));
        }
        value.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        value.setDelFlag(1L);
        applyDAO.save(value);

        //保存待审核消息 认证
        String auditDsc = "";
        List<IStaticDataValue> staticDataValues = staticDataCache.getStaticDataByCode("ORG_TYPE");
        int length = staticDataValues.size();
        for(int i = 0;i<length ;i++){
            IStaticDataValue staticDataValue = staticDataValues.get(i);
            if(staticDataValue.getCodeValue().equals(String.valueOf(value.getOrgType()))){
                auditDsc = staticDataValue.getCodeName()+"认证";
            }
        }
        auditSV.saveAudit(userId,value.getApplyId(),1L,auditDsc,1L);
    }

    /**
     * 查询认证信息
     * @param applyId
     * @return
     * @throws Exception
     */
    public IApplyValue queryApplyById(long applyId)throws Exception{
        if(applyId > 0 ){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IApplyValue.S_ApplyId,applyId,condition,params,false);
            List<IApplyValue> list = applyDAO.queryApplyByCondition(condition.toString(),params,-1,-1);
            if(list != null && list.size() >0){
                return list.get(0);
            }
        }
        return null;

    }
}
