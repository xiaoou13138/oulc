package com.ncu.table.ivalue;

import java.util.Date;

public interface IAuditValue{
  public final static String S_AuditId = "AUDIT_ID";
  public final static String S_ApplyId = "APPLY_ID";
  public final static String S_ApplyUserId = "APPLY_USER_ID";
  public final static String S_ApplyType = "APPLY_TYPE";
  public final static String S_AuditDsc = "AUDIT_DSC";
  public final static String S_Reason = "REASON";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_State = "STATE";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setAuditId(Long value);
  public void setApplyId(Long value);
  public void setApplyUserId(Long value);
  public void setApplyType(Long value);
  public void setAuditDsc(String value);
  public void setReason(String value);
  public void setCreateDate(Date value);
  public void setState(Long value);
  public void setDelFlag(Long value);
  public Long getAuditId();
  public Long getApplyId();
  public Long getApplyUserId();
  public Long getApplyType();
  public String getAuditDsc();
  public String getReason();
  public Date getCreateDate();
  public Long getState();
  public Long getDelFlag();
}