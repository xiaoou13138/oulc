package com.ncu.table.ivalue;

import java.util.Date;

public interface ILevalValue{
  public final static String S_LevalId = "LEVAL_ID";
  public final static String S_EntityId = "ENTITY_ID";
  public final static String S_EntityType = "ENTITY_TYPE";
  public final static String S_Leval = "LEVAL";
  public final static String S_CreateDate = "CREATE_DATE";
  public void setLevalId(Long value);
  public void setEntityId(Long value);
  public void setEntityType(Long value);
  public void setLeval(Long value);
  public void setCreateDate(Date value);
  public Long getLevalId();
  public Long getEntityId();
  public Long getEntityType();
  public Long getLeval();
  public Date getCreateDate();
}