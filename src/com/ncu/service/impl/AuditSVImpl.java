package com.ncu.service.impl;

import com.ncu.cache.StaticDataCache;
import com.ncu.dao.interfaces.IAuditDAO;
import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.service.interfaces.*;
import com.ncu.table.bean.AuditBean;
import com.ncu.table.bean.CatalogBean;
import com.ncu.table.bean.CatalogEntityRelBean;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.table.ivalue.*;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import com.ncu.util.UserMetaData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Service("AuditSVImpl")
public class AuditSVImpl implements IAuditSV {
    @Resource(name="AuditDAOImpl")
    private IAuditDAO auditDAO;

    @Resource(name="CommonDAOImpl")
    private ICommonDAO commonDAO;

    @Resource(name="UserSVImpl")
    private IUserSV userSV;

    @Resource(name="OrgDefSVImpl")
    private IOrgDefSV orgDefSV;

    @Resource(name="OrgUserRelSVImpl")
    private IOrgUserRelSV orgUserRelSV;

    @Resource(name="CatalogSVImpl")
    private ICatalogSV catalogSV;

    @Resource(name="CatalogEntityRelSVImpl")
    private ICatalogEntityRelSV catalogEntityRelSV;

    @Resource(name="ReportSVImpl")
    private IReportSV reportSV;

    @Autowired
    private StaticDataCache staticDataCache;

    /**
     * 保存待审核信息
     * @param userId 用户主键
     * @param applyId 申请的详细主键
     * @param applyType 申请类型 1是特殊账号认证申请 2是举报
     * @param auditDsc 审核展示的消息
     * @param state 审核状态 1是待审核 2是通过 3是拒绝
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAudit(long userId,long applyId,long applyType,String auditDsc,long state)throws Exception{
        IAuditValue bean = new AuditBean();
        bean.setDelFlag(1L);
        bean.setApplyId(applyId);
        bean.setApplyUserId(userId);
        bean.setApplyType(applyType);
        bean.setAuditDsc(auditDsc);
        bean.setState(state);
        bean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        auditDAO.save(bean);
    }
    /**
     * 查询待审核数据
     * @param userId 申请人的主键
     * @return
     * @throws Exception
     */
    public List<IAuditValue> queryAuditByApplyUserId(long userId,int begin,int end)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        if(userId>0){
            SQLCon.connectSQL(IAuditValue.S_ApplyUserId,userId,condition,params,false);
        }
        SQLCon.connectSQL(IAuditValue.S_DelFlag,1L,condition,params,false);
        return auditDAO.queryAuditByCondition(condition.toString(),params,begin,end);
    }
    public long queryAuditCountByApplyUserId(long userId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        if(userId>0){
            SQLCon.connectSQL(IAuditValue.S_ApplyUserId,userId,condition,params,false);
            SQLCon.connectSQL(IAuditValue.S_DelFlag,1L,condition,params,false);
        }
        return auditDAO.queryAuditCountByCondition(condition.toString(),params);
    }

    /**
     * 模糊查询待审核数据
     * @param searchContent
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryAuditInfoBySearchContent(String searchContent, int begin, int end) throws Exception{
        HashMap rtnMap = new HashMap();
        List <IAuditValue> rtnValue = null;
        long count = 0;
        if(StringUtils.isBlank(searchContent)){
            rtnValue = queryAuditByApplyUserId(-1L,begin,end);
            count = queryAuditCountByApplyUserId(-1L);
        }else{
            if(StringUtils.isNumeric(searchContent)){
                rtnValue = queryAuditByApplyUserId(Long.parseLong(searchContent),begin,end);
                count = queryAuditCountByApplyUserId(Long.parseLong(searchContent));
            }else{
                //先查询用户的信息 根据名称查
                List<IUserValue> userValueList = userSV.queryUserInfo(searchContent);
                if(userValueList != null && userValueList.size()>0){
                    int length = userValueList.size();
                    ArrayList userIds = new ArrayList();
                    for(int i = 0;i<length;i++){
                        userIds.add(userValueList.get(i).getUserId());
                    }

                    ArrayList<ParamsDefine> paramList= new ArrayList();
                    String sql = "from AuditBean a where a.applyUserId in (:applyUserIds) and a.delFlag = :delFlag";
                    ParamsDefine paramsDefine = new ParamsDefine();
                    paramsDefine.setIsList(true);
                    paramsDefine.setColName("applyUserIds");
                    paramsDefine.setParamListVal(userIds);
                    paramList.add(paramsDefine);

                    ParamsDefine delFlagParamsDefine = new ParamsDefine();
                    delFlagParamsDefine.setIsList(false);
                    delFlagParamsDefine.setColName("delFlag");
                    delFlagParamsDefine.setParamVal(1L);
                    paramList.add(delFlagParamsDefine);
                    rtnValue = commonDAO.commonQuery(sql,paramList.toArray(new ParamsDefine[0]),begin,end);
                    count = commonDAO.getCount(sql,paramList.toArray(new ParamsDefine[0]));

                }
            }
        }
        rtnMap.put("count",count);
        if(rtnValue != null && rtnValue.size()>0){
            ArrayList rtnList= new ArrayList();
            int length = rtnValue.size();
            for(int i =0;i<length;i++){
                IAuditValue auditValue = rtnValue.get(i);
                HashMap map = new HashMap();
                map.put("auditId",auditValue.getAuditId());
                map.put("auditType",staticDataCache.getCodeNameByCodeTypeAndValue("auditType",String.valueOf(auditValue.getApplyType())));
                map.put("applyUserId",auditValue.getApplyUserId());
                map.put("applyUserName",userSV.queryUserNameByUserId(auditValue.getApplyUserId()));
                map.put("auditDsc",auditValue.getAuditDsc());
                map.put("detail","123");
                map.put("time",TimeUtil.formatTimeyyyyMMddhhmmss(auditValue.getCreateDate()));
                rtnList.add(map);
            }
            rtnMap.put("auditList",rtnList);
        }


        return rtnMap;
    }
    /**
     * 处理审核数据
     * @param auditId
     * @param state
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void dealAudit(long auditId,long state) throws Exception{
        //先要判断审核的是什么数据 什么类型的数据
        IAuditValue auditValue = queryAuditById(auditId);
        if(auditValue == null){
            throw new Exception("待审核数据不存在");
        }
        if(auditValue.getApplyType() == 1 &&state == 2){//1是特殊账号的审核
            long userId = auditValue.getApplyUserId();
            //修改账号类型
            userSV.changeUserType(userId, UserMetaData.CHARITY_USER);
            //保存组织的信息
            long orgId = orgDefSV.saveOrgInfo(auditValue.getApplyId());
            //保存组织和账号的关系
            orgUserRelSV.saveOrgUserRel(orgId,userId);
            //创建目录
            ICatalogValue catalogValue = new CatalogBean();
            catalogValue.setDelFlag(1L);
            catalogValue.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
            catalogValue.setCatalogDesc("");
            catalogValue.setCatalogName("");
            catalogValue.setLeval(1L);
            catalogValue.setUpCatalogId(0L);
            catalogSV.saveCatalog(catalogValue);

            //保存组织和目录的关系
            ICatalogEntityRelValue catalogEntityRelValue = new CatalogEntityRelBean();
            catalogEntityRelValue.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
            catalogEntityRelValue.setEntityId(orgId);
            catalogEntityRelValue.setEntityType(1L);
            catalogEntityRelValue.setCatalogId(catalogValue.getCatalogId());
            catalogEntityRelSV.saveCatalogEntityRel(catalogEntityRelValue);

        }else if(auditValue.getApplyType() == 2){//举报信息的审核
            //查举报详细信息
            IReportValue reportValue =reportSV.queryReportById(auditValue.getApplyId());
            if(reportValue ==null){
                throw new Exception("举报信息不存在");
            }
            long userId = reportValue.getUserId();//举报人
            long reportUserId =reportValue.getReportEntityId();//被举报人
            IUserValue userValue = userSV.queryUserInfoByUserId(userId);
            IUserValue reportUserValue = userSV.queryUserInfoByUserId(reportUserId);
            if(state == 2){//通过 扣被举报人的分数 -1  加举报人的分数 +1
                float userScord = userValue.getScord()+1;
                float reportUserScord = userValue.getScord()-1;
                userValue.setScord(userScord);
                reportUserValue.setScord(reportUserScord);
                userSV.save(userValue);
                userSV.save(reportUserValue);
            }else if(state ==3){//不通过 扣举报人分数 -0.5
                reportUserValue.setScord((float)(reportUserValue.getScord()-0.5));
                userSV.save(reportUserValue);
            }
            reportValue.setDelFlag(0L);
            reportSV.save(reportValue);
        }
        //修改待审核数据的状态
        auditValue.setState(state);
        auditValue.setDelFlag(0L);
        auditDAO.save(auditValue);
    }

    /**
     * 查询待审核数据
     * @param auditId 待审核数据主键
     * @return
     * @throws Exception
     */
    public IAuditValue queryAuditById(long auditId) throws Exception{
        if(auditId > 0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IAuditValue.S_AuditId,auditId,condition,params,false);
            SQLCon.connectSQL(IAuditValue.S_DelFlag,1L,condition,params,false);
            List<IAuditValue> list= auditDAO.queryAuditByCondition(condition.toString(),params,-1,-1);
            if(list != null &list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }






}
