package com.ncu.service.impl;

import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.dao.interfaces.IReportDAO;
import com.ncu.service.interfaces.IAuditSV;
import com.ncu.service.interfaces.IReportSV;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.table.bean.ReportBean;
import com.ncu.table.ivalue.IReportValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/5/12.
 */
@Service("ReportSVImpl")
public class ReportSVImpl implements IReportSV {
    @Resource(name="ReportDAOImpl")
    private IReportDAO reportDAO;

    @Resource(name="AuditSVImpl")
    private IAuditSV auditSV;

    @Resource(name="CommonDAOImpl")
    private ICommonDAO commonDAO ;

    /**
     * 保存举报信息
     * @param content
     * @param userId
     * @param reportUserId
     * @param reportType
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveReport(String content,long userId,long reportUserId,long reportType)throws Exception{
        if(userId == reportUserId){
            throw new Exception("不能举报自己");
        }
        IReportValue reportValue = new ReportBean();
        reportValue.setUserId(userId);
        reportValue.setDsc(content);
        reportValue.setReportEntityId(reportUserId);
        reportValue.setEntityType(1L);//1是用户
        reportValue.setReportType(reportType);//1是举报公共账号
        reportValue.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        reportValue.setDelFlag(1L);
        reportDAO.save(reportValue);

        auditSV.saveAudit(userId,reportValue.getId(),2L,content,1);
    }

    /**
     * 查询举报信息
     * @param id
     * @return
     * @throws Exception
     */
    public IReportValue queryReportById(long id)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IReportValue.S_Id,id,condition,params,false);
        SQLCon.connectSQL(IReportValue.S_DelFlag,1L,condition,params,false);
        List<IReportValue> list = reportDAO.queryReportByCondition(condition.toString(),params,-1,-1);
        if(list != null  && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 保存信息
     * @param value
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(IReportValue value)throws Exception{
        reportDAO.save(value);
    }

    /**
     * 查询被举报的次数
     * @param userId
     * @return
     * @throws Exception
     */
    public long queryReportCountByUserId(long userId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IReportValue.S_Id,userId,condition,params,false);
        SQLCon.connectSQL(IReportValue.S_DelFlag,1L,condition,params,false);
        return reportDAO.queryReportCountByCondition(condition.toString(),params);
    }

    /**
     * 查询被举报并通过审核的次数
     * @param userId
     * @return
     * @throws Exception
     */
    public long querySuccessReportCountByUserId(long userId)throws Exception{
        String sql = "from AuditBean a,ReportBean b where a.applyId=b.id and a.state=2 and b.reportEntityId=:reportEntityId and b.entityType=1";
        ArrayList <ParamsDefine> paramsDefineArrayList = new ArrayList<>();
        ParamsDefine paramsDefine = new ParamsDefine();
        paramsDefine.setIsList(false);
        paramsDefine.setParamVal(userId);
        paramsDefine.setColName("reportEntityId");
        paramsDefineArrayList.add(paramsDefine);
        return commonDAO.getCount(sql,paramsDefineArrayList.toArray(new ParamsDefine[0]));
    }

    /**
     * 查询举报信息
     * @param userId
     * @return
     * @throws Exception
     */
    public HashMap queryReportInfoByUserId(long userId)throws Exception{
        long reportCount = queryReportCountByUserId(userId);
        long passReportCount = querySuccessReportCountByUserId(userId);
        HashMap rtnMap = new HashMap();
        rtnMap.put("reportCount",reportCount);
        rtnMap.put("passReportCount",passReportCount);
        return rtnMap;
    }
}
