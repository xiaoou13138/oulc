package com.ncu.table.ivalue;

import java.util.Date;

public interface ICatalogEntityRelValue{
  public final static String S_RelId = "REL_ID";
  public final static String S_EntityId = "ENTITY_ID";
  public final static String S_EntityType = "ENTITY_TYPE";
  public final static String S_CatalogId = "CATALOG_ID";
  public final static String S_CreateDate = "CREATE_DATE";
  public void setRelId(Long value);
  public void setEntityId(Long value);
  public void setEntityType(Long value);
  public void setCatalogId(Long value);
  public void setCreateDate(Date value);
  public Long getRelId();
  public Long getEntityId();
  public Long getEntityType();
  public Long getCatalogId();
  public Date getCreateDate();
}