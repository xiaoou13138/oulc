package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IAuditValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
public interface IAuditSV {
    /**
     * 保存待审核信息
     * @param userId 用户主键
     * @param applyId 申请的详细主键
     * @param applyType 申请类型 1是特殊账号认证申请
     * @param auditDsc 审核展示的消息
     * @param state 审核状态 1是待审核 2是通过 3是拒绝
     * @throws Exception
     */
    public void saveAudit(long userId,long applyId,long applyType,String auditDsc,long state)throws Exception;

    /**
     * 模糊查询待审核数据
     * @param searchContent
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryAuditInfoBySearchContent(String searchContent,int begin,int end) throws Exception;

    /**
     * 处理审核数据
     * @param auditId
     * @param state
     * @throws Exception
     */
    public void dealAudit(long auditId,long state) throws Exception;

    /**
     * 查询待审核数据
     * @param auditId 待审核数据主键
     * @return
     * @throws Exception
     */
    public IAuditValue queryAuditById(long auditId) throws Exception;

    /**
     * 查询待审核数据
     * @param userId 申请人的主键
     * @return
     * @throws Exception
     */
    public List<IAuditValue> queryAuditByApplyUserId(long userId,int begin,int end)throws Exception;
    public long queryAuditCountByApplyUserId(long userId)throws Exception;

}
