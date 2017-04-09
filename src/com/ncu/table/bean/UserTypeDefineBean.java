package com.ncu.table.bean;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.ncu.util.beanUtil.BeanUtil;
import java.util.Date;
import com.ncu.table.ivalue.IUserTypeDefineValue;

@Entity
@Table(name ="user_type_define")
public class UserTypeDefineBean implements IUserTypeDefineValue{

  @Id
  @Column(name = IUserTypeDefineValue.S_UserType)
  private String userType;

  @Column(name = IUserTypeDefineValue.S_TypeDsc)
  private String typeDsc;

  @Column(name = IUserTypeDefineValue.S_SecType)
  private String secType;

  @Column(name = IUserTypeDefineValue.S_DelFlag)
  private int delFlag;

  @Column(name = IUserTypeDefineValue.S_CreateDate)
  private Date createDate;

  @Column(name = IUserTypeDefineValue.S_ModifyDate)
  private Date modifyDate;

  @Transient
  public static Class beanClass = UserTypeDefineBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setUserType(String value){
    this.userType = value;
  }

  public String getUserType(){
    return userType;
  }

  public void setTypeDsc(String value){
    this.typeDsc = value;
  }

  public String getTypeDsc(){
    return typeDsc;
  }

  public void setSecType(String value){
    this.secType = value;
  }

  public String getSecType(){
    return secType;
  }

  public void setDelFlag(int value){
    this.delFlag = value;
  }

  public int getDelFlag(){
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