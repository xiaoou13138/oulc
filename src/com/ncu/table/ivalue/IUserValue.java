package com.ncu.table.ivalue;

import java.util.Date;

public interface IUserValue{
  public final static String S_UserId = "USER_ID";
  public final static String S_Code = "CODE";
  public final static String S_Password = "PASSWORD";
  public final static String S_DelFlag = "DEL_FLAG";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_UserType = "USER_TYPE";
  public final static String S_ModifyDate = "MODIFY_DATE";
  public final static String S_Name = "NAME";
  public final static String S_Phone = "PHONE";
  public final static String S_Sex = "SEX";
  public final static String S_RealName = "REAL_NAME";
  public void setUserId(Long value);
  public void setCode(String value);
  public void setPassword(String value);
  public void setDelFlag(Long value);
  public void setCreateDate(Date value);
  public void setUserType(String value);
  public void setModifyDate(Date value);
  public void setName(String value);
  public void setPhone(String value);
  public void setSex(String value);
  public void setRealName(String value);
  public Long getUserId();
  public String getCode();
  public String getPassword();
  public Long getDelFlag();
  public Date getCreateDate();
  public String getUserType();
  public Date getModifyDate();
  public String getName();
  public String getPhone();
  public String getSex();
  public String getRealName();
}