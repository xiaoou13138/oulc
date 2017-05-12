package com.ncu.table.ivalue;

import java.util.Date;

public interface IOrgDefValue{
  public final static String S_OrgId = "ORG_ID";
  public final static String S_OrgName = "ORG_NAME";
  public final static String S_OrgDesc = "ORG_DESC";
  public final static String S_City = "CITY";
  public final static String S_DetailAddress = "DETAIL_ADDRESS";
  public final static String S_DelFlag = "DEL_FLAG";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_ModifyDate = "MODIFY_DATE";
  public void setOrgId(Long value);
  public void setOrgName(String value);
  public void setOrgDesc(String value);
  public void setCity(String value);
  public void setDetailAddress(String value);
  public void setDelFlag(Long value);
  public void setCreateDate(Date value);
  public void setModifyDate(Date value);
  public Long getOrgId();
  public String getOrgName();
  public String getOrgDesc();
  public String getCity();
  public String getDetailAddress();
  public Long getDelFlag();
  public Date getCreateDate();
  public Date getModifyDate();
}