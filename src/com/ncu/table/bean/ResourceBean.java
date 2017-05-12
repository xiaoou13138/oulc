package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IResourceValue;

@Entity
@Table(name ="resource")
public class ResourceBean implements IResourceValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IResourceValue.S_ResourceId)
  private Long resourceId;

  @Column(name = IResourceValue.S_Name)
  private String name;

  @Column(name = IResourceValue.S_Num)
  private Long num;

  @Column(name = IResourceValue.S_Unit)
  private String unit;

  @Column(name = IResourceValue.S_Description)
  private String description;

  @Column(name = IResourceValue.S_CreateData)
  private Date createData;

  @Column(name = IResourceValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = ResourceBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setResourceId(Long value){
    this.resourceId = value;
  }

  public Long getResourceId(){
    return resourceId;
  }

  public void setName(String value){
    this.name = value;
  }

  public String getName(){
    return name;
  }

  public void setNum(Long value){
    this.num = value;
  }

  public Long getNum(){
    return num;
  }

  public void setUnit(String value){
    this.unit = value;
  }

  public String getUnit(){
    return unit;
  }

  public void setDescription(String value){
    this.description = value;
  }

  public String getDescription(){
    return description;
  }

  public void setCreateData(Date value){
    this.createData = value;
  }

  public Date getCreateData(){
    return createData;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

}