package com.ncu.table.ivalue;

import java.util.Date;

public interface IWebInfoValue{
  public final static String S_Id = "ID";
  public final static String S_Title = "TITLE";
  public final static String S_HeadContent = "HEAD_CONTENT";
  public final static String S_Url = "URL";
  public final static String S_DelFlag = "DEL_FLAG";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_OperId = "OPER_ID";
  public void setId(Long value);
  public void setTitle(String value);
  public void setHeadContent(String value);
  public void setUrl(String value);
  public void setDelFlag(Long value);
  public void setCreateDate(Date value);
  public void setOperId(Long value);
  public Long getId();
  public String getTitle();
  public String getHeadContent();
  public String getUrl();
  public Long getDelFlag();
  public Date getCreateDate();
  public Long getOperId();
}