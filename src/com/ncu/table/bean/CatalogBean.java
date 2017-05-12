package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.ICatalogValue;

@Entity
@Table(name ="catalog")
public class CatalogBean implements ICatalogValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ICatalogValue.S_CatalogId)
  private Long catalogId;

  @Column(name = ICatalogValue.S_Leval)
  private Long leval;

  @Column(name = ICatalogValue.S_UpCatalogId)
  private Long upCatalogId;

  @Column(name = ICatalogValue.S_CatalogName)
  private String catalogName;

  @Column(name = ICatalogValue.S_CatalogDesc)
  private String catalogDesc;

  @Column(name = ICatalogValue.S_DelFlag)
  private Long delFlag;

  @Column(name = ICatalogValue.S_CreateDate)
  private Date createDate;

  @Transient
  public static Class beanClass = CatalogBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setCatalogId(Long value){
    this.catalogId = value;
  }

  public Long getCatalogId(){
    return catalogId;
  }

  public void setLeval(Long value){
    this.leval = value;
  }

  public Long getLeval(){
    return leval;
  }

  public void setUpCatalogId(Long value){
    this.upCatalogId = value;
  }

  public Long getUpCatalogId(){
    return upCatalogId;
  }

  public void setCatalogName(String value){
    this.catalogName = value;
  }

  public String getCatalogName(){
    return catalogName;
  }

  public void setCatalogDesc(String value){
    this.catalogDesc = value;
  }

  public String getCatalogDesc(){
    return catalogDesc;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

}