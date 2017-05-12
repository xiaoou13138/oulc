package com.ncu.table.ivalue;

import java.util.Date;

public interface IReportValue{
  public final static String S_Id = "ID";
  public final static String S_UserId = "USER_ID";
  public final static String S_Dsc = "DSC";
  public final static String S_ReportEntityId = "REPORT_ENTITY_ID";
  public final static String S_EntityType = "ENTITY_TYPE";
  public final static String S_ReportType = "REPORT_TYPE";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setId(Long value);
  public void setUserId(Long value);
  public void setDsc(String value);
  public void setReportEntityId(Long value);
  public void setEntityType(Long value);
  public void setReportType(Long value);
  public void setCreateDate(Date value);
  public void setDelFlag(Long value);
  public Long getId();
  public Long getUserId();
  public String getDsc();
  public Long getReportEntityId();
  public Long getEntityType();
  public Long getReportType();
  public Date getCreateDate();
  public Long getDelFlag();
}