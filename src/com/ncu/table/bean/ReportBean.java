package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IReportValue;

@Entity
@Table(name ="report")
public class ReportBean implements IReportValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IReportValue.S_Id)
  private Long id;

  @Column(name = IReportValue.S_UserId)
  private Long userId;

  @Column(name = IReportValue.S_Dsc)
  private String dsc;

  @Column(name = IReportValue.S_ReportEntityId)
  private Long reportEntityId;

  @Column(name = IReportValue.S_EntityType)
  private Long entityType;

  @Column(name = IReportValue.S_ReportType)
  private Long reportType;

  @Column(name = IReportValue.S_CreateDate)
  private Date createDate;

  @Column(name = IReportValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = ReportBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setId(Long value){
    this.id = value;
  }

  public Long getId(){
    return id;
  }

  public void setUserId(Long value){
    this.userId = value;
  }

  public Long getUserId(){
    return userId;
  }

  public void setDsc(String value){
    this.dsc = value;
  }

  public String getDsc(){
    return dsc;
  }

  public void setReportEntityId(Long value){
    this.reportEntityId = value;
  }

  public Long getReportEntityId(){
    return reportEntityId;
  }

  public void setEntityType(Long value){
    this.entityType = value;
  }

  public Long getEntityType(){
    return entityType;
  }

  public void setReportType(Long value){
    this.reportType = value;
  }

  public Long getReportType(){
    return reportType;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

}