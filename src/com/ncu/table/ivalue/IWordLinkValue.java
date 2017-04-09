package com.ncu.table.ivalue;

import java.util.Date;

public interface IWordLinkValue{
  public final static String S_RelId = "REL_ID";
  public final static String S_WordId = "WORD_ID";
  public final static String S_WebInfoId = "WEB_INFO_ID";
  public final static String S_DelFlag = "DEL_FLAG";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_ModifyDate = "MODIFY_DATE";
  public void setRelId(int value);
  public void setWordId(int value);
  public void setWebInfoId(int value);
  public void setDelFlag(int value);
  public void setCreateDate(Date value);
  public void setModifyDate(Date value);
  public int getRelId();
  public int getWordId();
  public int getWebInfoId();
  public int getDelFlag();
  public Date getCreateDate();
  public Date getModifyDate();
}