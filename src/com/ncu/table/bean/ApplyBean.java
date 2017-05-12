package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IApplyValue;

@Entity
@Table(name ="apply")
public class ApplyBean implements IApplyValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IApplyValue.S_ApplyId)
  private Long applyId;

  @Column(name = IApplyValue.S_OrgName)
  private String orgName;

  @Column(name = IApplyValue.S_OrgType)
  private Long orgType;

  @Column(name = IApplyValue.S_City)
  private String city;

  @Column(name = IApplyValue.S_DetailAddress)
  private String detailAddress;

  @Column(name = IApplyValue.S_OrgDsc)
  private String orgDsc;

  @Column(name = IApplyValue.S_CertificationDsc)
  private String certificationDsc;

  @Column(name = IApplyValue.S_ApplyUserName)
  private String applyUserName;

  @Column(name = IApplyValue.S_ApplyUserCertCode)
  private String applyUserCertCode;

  @Column(name = IApplyValue.S_ApplyUserPhone)
  private String applyUserPhone;

  @Column(name = IApplyValue.S_ApplyUserMail)
  private String applyUserMail;

  @Column(name = IApplyValue.S_CreateDate)
  private Date createDate;

  @Column(name = IApplyValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = ApplyBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setApplyId(Long value){
    this.applyId = value;
  }

  public Long getApplyId(){
    return applyId;
  }

  public void setOrgName(String value){
    this.orgName = value;
  }

  public String getOrgName(){
    return orgName;
  }

  public void setOrgType(Long value){
    this.orgType = value;
  }

  public Long getOrgType(){
    return orgType;
  }

  public void setCity(String value){
    this.city = value;
  }

  public String getCity(){
    return city;
  }

  public void setDetailAddress(String value){
    this.detailAddress = value;
  }

  public String getDetailAddress(){
    return detailAddress;
  }

  public void setOrgDsc(String value){
    this.orgDsc = value;
  }

  public String getOrgDsc(){
    return orgDsc;
  }

  public void setCertificationDsc(String value){
    this.certificationDsc = value;
  }

  public String getCertificationDsc(){
    return certificationDsc;
  }

  public void setApplyUserName(String value){
    this.applyUserName = value;
  }

  public String getApplyUserName(){
    return applyUserName;
  }

  public void setApplyUserCertCode(String value){
    this.applyUserCertCode = value;
  }

  public String getApplyUserCertCode(){
    return applyUserCertCode;
  }

  public void setApplyUserPhone(String value){
    this.applyUserPhone = value;
  }

  public String getApplyUserPhone(){
    return applyUserPhone;
  }

  public void setApplyUserMail(String value){
    this.applyUserMail = value;
  }

  public String getApplyUserMail(){
    return applyUserMail;
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