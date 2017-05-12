package com.ncu.table.ivalue;

import java.util.Date;

public interface IResourceValue{
  public final static String S_ResourceId = "RESOURCE_ID";
  public final static String S_Name = "NAME";
  public final static String S_Num = "NUM";
  public final static String S_Unit = "UNIT";
  public final static String S_Description = "DESCRIPTION";
  public final static String S_CreateData = "CREATE_DATA";
  public final static String S_DelFlag = "DEL_FLAG";
  public void setResourceId(Long value);
  public void setName(String value);
  public void setNum(Long value);
  public void setUnit(String value);
  public void setDescription(String value);
  public void setCreateData(Date value);
  public void setDelFlag(Long value);
  public Long getResourceId();
  public String getName();
  public Long getNum();
  public String getUnit();
  public String getDescription();
  public Date getCreateData();
  public Long getDelFlag();
}