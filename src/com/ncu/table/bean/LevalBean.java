package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.ILevalValue;

@Entity
@Table(name ="leval")
public class LevalBean implements ILevalValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ILevalValue.S_LevalId)
  private Long levalId;

  @Column(name = ILevalValue.S_EntityId)
  private Long entityId;

  @Column(name = ILevalValue.S_EntityType)
  private Long entityType;

  @Column(name = ILevalValue.S_Leval)
  private Long leval;

  @Column(name = ILevalValue.S_CreateDate)
  private Date createDate;

  @Transient
  public static Class beanClass = LevalBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setLevalId(Long value){
    this.levalId = value;
  }

  public Long getLevalId(){
    return levalId;
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

  public void setLeval(Long value){
    this.leval = value;
  }

  public Long getLeval(){
    return leval;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

}