package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.ICatalogEntityRelValue;

@Entity
@Table(name ="catalog_entity_rel")
public class CatalogEntityRelBean implements ICatalogEntityRelValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ICatalogEntityRelValue.S_RelId)
  private Long relId;

  @Column(name = ICatalogEntityRelValue.S_EntityId)
  private Long entityId;

  @Column(name = ICatalogEntityRelValue.S_EntityType)
  private Long entityType;

  @Column(name = ICatalogEntityRelValue.S_CatalogId)
  private Long catalogId;

  @Column(name = ICatalogEntityRelValue.S_CreateDate)
  private Date createDate;

  @Transient
  public static Class beanClass = CatalogEntityRelBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setRelId(Long value){
    this.relId = value;
  }

  public Long getRelId(){
    return relId;
  }

  public void setEntityId(Long value){
    this.entityId = value;
  }

  public Long getEntityId(){
    return entityId;
  }

  public void setEntityType(Long value){
    this.entityType = value;
  }

  public Long getEntityType(){
    return entityType;
  }

  public void setCatalogId(Long value){
    this.catalogId = value;
  }

  public Long getCatalogId(){
    return catalogId;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

}