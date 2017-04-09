package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IFriendValue;

@Entity
@Table(name ="friend")
public class FriendBean implements IFriendValue,Serializable{

  @Id
  @Column(name = IFriendValue.S_UserId)
  private Long userId;

  @Id
  @Column(name = IFriendValue.S_FriendUserId)
  private Long friendUserId;

  @Column(name = IFriendValue.S_CreateDate)
  private Date createDate;

  @Transient
  public static Class beanClass = FriendBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setUserId(Long value){
    this.userId = value;
  }

  public Long getUserId(){
    return userId;
  }

  public void setFriendUserId(Long value){
    this.friendUserId = value;
  }

  public Long getFriendUserId(){
    return friendUserId;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

}