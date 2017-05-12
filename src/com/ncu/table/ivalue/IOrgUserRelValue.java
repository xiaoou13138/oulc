package com.ncu.table.ivalue;

import java.util.Date;

public interface IOrgUserRelValue{
  public final static String S_RelId = "REL_ID";
  public final static String S_OrgId = "ORG_ID";
  public final static String S_UserId = "USER_ID";
  public final static String S_CreateDate = "CREATE_DATE";
  public void setRelId(Long value);
  public void setOrgId(Long value);
  public void setUserId(Long value);
  public void setCreateDate(Date value);
  public Long getRelId();
  public Long getOrgId();
  public Long getUserId();
  public Date getCreateDate();
}