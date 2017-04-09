package com.ncu.table.ivalue;

import java.util.Date;

public interface IWordValue{
  public final static String S_WordId = "WORD_ID";
  public final static String S_Word = "WORD";
  public final static String S_WordDesc = "WORD_DESC";
  public final static String S_DelFlag = "DEL_FLAG";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_ModifyDate = "MODIFY_DATE";
  public void setWordId(int value);
  public void setWord(String value);
  public void setWordDesc(String value);
  public void setDelFlag(int value);
  public void setCreateDate(Date value);
  public void setModifyDate(Date value);
  public int getWordId();
  public String getWord();
  public String getWordDesc();
  public int getDelFlag();
  public Date getCreateDate();
  public Date getModifyDate();
}