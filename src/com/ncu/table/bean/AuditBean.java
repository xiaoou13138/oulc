package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IAuditValue;

@Entity
@Table(name ="audit")
public class AuditBean implements IAuditValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IAuditValue.S_AuditId)
  private Long auditId;

  @Column(name = IAuditValue.S_ApplyId)
  private Long applyId;

  @Column(name = IAuditValue.S_ApplyUserId)
  private Long applyUserId;

  @Column(name = IAuditValue.S_ApplyType)
  private Long applyType;

  @Column(name = IAuditValue.S_AuditDsc)
  private String auditDsc;

  @Column(name = IAuditValue.S_Reason)
  private String reason;

  @Column(name = IAuditValue.S_CreateDate)
  private Date createDate;

  @Column(name = IAuditValue.S_State)
  private Long state;

  @Column(name = IAuditValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = AuditBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setAuditId(Long value){
    this.auditId = value;
  }

  public Long getAuditId(){
    return auditId;
  }

  public void setApplyId(Long value){
    this.applyId = value;
  }

  public Long getApplyId(){
    return applyId;
  }

  public void setApplyUserId(Long value){
    this.applyUserId = value;
  }

  public Long getApplyUserId(){
    return applyUserId;
  }

  public void setApplyType(Long value){
    this.applyType = value;
  }

  public Long getApplyType(){
    return applyType;
  }

  public void setAuditDsc(String value){
    this.auditDsc = value;
  }

  public String getAuditDsc(){
    return auditDsc;
  }

  public void setReason(String value){
    this.reason = value;
  }

  public String getReason(){
    return reason;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

  public void setState(Long value){
    this.state = value;
  }

  public Long getState(){
    return state;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

}