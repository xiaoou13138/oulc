package com.ncu.table.ivalue;

import java.util.Date;

public interface IMessageValue{
  public final static String S_Id = "ID";
  public final static String S_UserIdSmall = "USER_ID_SMALL";
  public final static String S_UserIdBig = "USER_ID_BIG";
  public final static String S_Content = "CONTENT";
  public final static String S_SendPeople = "SEND_PEOPLE";
  public final static String S_CreateDate = "CREATE_DATE";
  public void setId(Long value);
  public void setUserIdSmall(Long value);
  public void setUserIdBig(Long value);
  public void setContent(String value);
  public void setSendPeople(Long value);
  public void setCreateDate(Date value);
  public Long getId();
  public Long getUserIdSmall();
  public Long getUserIdBig();
  public String getContent();
  public Long getSendPeople();
  public Date getCreateDate();
}