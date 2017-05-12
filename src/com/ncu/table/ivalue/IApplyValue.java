package com.ncu.table.ivalue;

import java.util.Date;

public interface IApplyValue{
  public final static String S_ApplyId = "APPLY_ID";
  public final static String S_OrgName = "ORG_NAME";
  public final static String S_OrgType = "ORG_TYPE";
  public final static String S_City = "CITY";
  public final static String S_DetailAddress = "DETAIL_ADDRESS";
  public final static String S_OrgDsc = "ORG_DSC";
  public final static String S_CertificationDsc = "CERTIFICATION_DSC";
  public final static String S_ApplyUserName = "APPLY_USER_NAME";
  public final static String S_ApplyUserCertCode = "APPLY_USER_CERT_CODE";
  public final static String S_ApplyUserPhone = "APPLY_USER_PHONE";
  public final static String S_ApplyUserMail = "APPLY_USER_MAIL";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setApplyId(Long value);
  public void setOrgName(String value);
  public void setOrgType(Long value);
  public void setCity(String value);
  public void setDetailAddress(String value);
  public void setOrgDsc(String value);
  public void setCertificationDsc(String value);
  public void setApplyUserName(String value);
  public void setApplyUserCertCode(String value);
  public void setApplyUserPhone(String value);
  public void setApplyUserMail(String value);
  public void setCreateDate(Date value);
  public void setDelFlag(Long value);
  public Long getApplyId();
  public String getOrgName();
  public Long getOrgType();
  public String getCity();
  public String getDetailAddress();
  public String getOrgDsc();
  public String getCertificationDsc();
  public String getApplyUserName();
  public String getApplyUserCertCode();
  public String getApplyUserPhone();
  public String getApplyUserMail();
  public Date getCreateDate();
  public Long getDelFlag();
}