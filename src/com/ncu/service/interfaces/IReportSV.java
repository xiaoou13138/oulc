package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IReportValue;

import java.util.HashMap;

/**
 * Created by xiaoou on 2017/5/12.
 */
public interface IReportSV {
    /**
     * 保存举报信息
     * @param content
     * @param userId
     * @param reportUserId
     * @param reportType
     * @throws Exception
     */
    public void saveReport(String content,long userId,long reportUserId,long reportType)throws Exception;

    /**
     * 查询举报信息
     * @param id
     * @return
     * @throws Exception
     */
    public IReportValue queryReportById(long id)throws Exception;

    /**
     * 保存信息
     * @param value
     * @throws Exception
     */
    public void save(IReportValue value)throws Exception;

    /**
     * 查询被举报的次数
     * @param userId
     * @return
     * @throws Exception
     */
    public long queryReportCountByUserId(long userId)throws Exception;

    /**
     * 查询被举报并通过审核的次数
     * @param userId
     * @return
     * @throws Exception
     */
    public long querySuccessReportCountByUserId(long userId)throws Exception;

    /**
     * 查询举报信息
     * @param userId
     * @return
     * @throws Exception
     */
    public HashMap queryReportInfoByUserId(long userId)throws Exception;
}
