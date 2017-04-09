package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IUserValue;

@Entity
@Table(name ="user")
public class UserBean implements IUserValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IUserValue.S_UserId)
  private Long userId;

  @Column(name = IUserValue.S_Code)
  private String code;

  @Column(name = IUserValue.S_Password)
  private String password;

  @Column(name = IUserValue.S_DelFlag)
  private Long delFlag;

  @Column(name = IUserValue.S_CreateDate)
  private Date createDate;

  @Column(name = IUserValue.S_UserType)
  private String userType;

  @Column(name = IUserValue.S_ModifyDate)
  private Date modifyDate;

  @Column(name = IUserValue.S_Name)
  private String name;

  @Column(name = IUserValue.S_Phone)
  private String phone;

  @Column(name = IUserValue.S_Sex)
  private String sex;

  @Column(name = IUserValue.S_RealName)
  private String realName;

  @Transient
  public static Class beanClass = UserBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setUserId(Long value){
    this.userId = value;
  }

  public Long getUserId(){
    return userId;
  }

  public void setCode(String value){
    this.code = value;
  }

  public String getCode(){
    return code;
  }

  public void setPassword(String value){
    this.password = value;
  }

  public String getPassword(){
    return password;
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

  public void setUserType(String value){
    this.userType = value;
  }

  public String getUserType(){
    return userType;
  }

  public void setModifyDate(Date value){
    this.modifyDate = value;
  }

  public Date getModifyDate(){
    return modifyDate;
  }

  public void setName(String value){
    this.name = value;
  }

  public String getName(){
    return name;
  }

  public void setPhone(String value){
    this.phone = value;
  }

  public String getPhone(){
    return phone;
  }

  public void setSex(String value){
    this.sex = value;
  }

  public String getSex(){
    return sex;
  }

  public void setRealName(String value){
    this.realName = value;
  }

  public String getRealName(){
    return realName;
  }

}