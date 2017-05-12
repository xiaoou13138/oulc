package com.ncu.table.ivalue;

import java.util.Date;

public interface ICatalogValue{
  public final static String S_CatalogId = "CATALOG_ID";
  public final static String S_Leval = "LEVAL";
  public final static String S_UpCatalogId = "UP_CATALOG_ID";
  public final static String S_CatalogName = "CATALOG_NAME";
  public final static String S_CatalogDesc = "CATALOG_DESC";
  public final static String S_DelFlag = "DEL_FLAG";
  public final static String S_CreateDate = "CREATE_DATE";
  public void setCatalogId(Long value);
  public void setLeval(Long value);
  public void setUpCatalogId(Long value);
  public void setCatalogName(String value);
  public void setCatalogDesc(String value);
  public void setDelFlag(Long value);
  public void setCreateDate(Date value);
  public Long getCatalogId();
  public Long getLeval();
  public Long getUpCatalogId();
  public String getCatalogName();
  public String getCatalogDesc();
  public Long getDelFlag();
  public Date getCreateDate();
}