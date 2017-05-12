package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IOrgDefValue;

@Entity
@Table(name ="org_def")
public class OrgDefBean implements IOrgDefValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IOrgDefValue.S_OrgId)
  private Long orgId;

  @Column(name = IOrgDefValue.S_OrgName)
  private String orgName;

  @Column(name = IOrgDefValue.S_OrgDesc)
  private String orgDesc;

  @Column(name = IOrgDefValue.S_City)
  private String city;

  @Column(name = IOrgDefValue.S_DetailAddress)
  private String detailAddress;

  @Column(name = IOrgDefValue.S_DelFlag)
  private Long delFlag;

  @Column(name = IOrgDefValue.S_CreateDate)
  private Date createDate;

  @Column(name = IOrgDefValue.S_ModifyDate)
  private Date modifyDate;

  @Transient
  public static Class beanClass = OrgDefBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setOrgId(Long value){
    this.orgId = value;
  }

  public Long getOrgId(){
    return orgId;
  }

  public void setOrgName(String value){
    this.orgName = value;
  }

  public String getOrgName(){
    return orgName;
  }

  public void setOrgDesc(String value){
    this.orgDesc = value;
  }

  public String getOrgDesc(){
    return orgDesc;
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

  public void setModifyDate(Date value){
    this.modifyDate = value;
  }

  public Date getModifyDate(){
    return modifyDate;
  }

}