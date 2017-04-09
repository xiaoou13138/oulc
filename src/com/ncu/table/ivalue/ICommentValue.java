package com.ncu.table.ivalue;

import java.util.Date;

public interface ICommentValue{
  public final static String S_Id = "ID";
  public final static String S_UserId = "USER_ID";
  public final static String S_Content = "CONTENT";
  public final static String S_EntityId = "ENTITY_ID";
  public final static String S_EntityType = "ENTITY_TYPE";
  public final static String S_CreateDate = "CREATE_DATE";
  public final static String S_DelFlag = "DEL_FLAG";
  public final static String S_ModifyDate = "MODIFY_DATE";
  public void setId(Long value);
  public void setUserId(Long value);
  public void setContent(String value);
  public void setEntityId(Long value);
  public void setEntityType(String value);
  public void setCreateDate(Date value);
  public void setDelFlag(Long value);
  public void setModifyDate(Date value);
  public Long getId();
  public Long getUserId();
  public String getContent();
  public Long getEntityId();
  public String getEntityType();
  public Date getCreateDate();
  public Long getDelFlag();
  public Date getModifyDate();
}