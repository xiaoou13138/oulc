package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IMessageValue;

@Entity
@Table(name ="message")
public class MessageBean implements IMessageValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IMessageValue.S_Id)
  private Long id;

  @Column(name = IMessageValue.S_UserIdSmall)
  private Long userIdSmall;

  @Column(name = IMessageValue.S_UserIdBig)
  private Long userIdBig;

  @Column(name = IMessageValue.S_Content)
  private String content;

  @Column(name = IMessageValue.S_SendPeople)
  private Long sendPeople;

  @Column(name = IMessageValue.S_CreateDate)
  private Date createDate;

  @Transient
  public static Class beanClass = MessageBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setId(Long value){
    this.id = value;
  }

  public Long getId(){
    return id;
  }

  public void setUserIdSmall(Long value){
    this.userIdSmall = value;
  }

  public Long getUserIdSmall(){
    return userIdSmall;
  }

  public void setUserIdBig(Long value){
    this.userIdBig = value;
  }

  public Long getUserIdBig(){
    return userIdBig;
  }

  public void setContent(String value){
    this.content = value;
  }

  public String getContent(){
    return content;
  }

  public void setSendPeople(Long value){
    this.sendPeople = value;
  }

  public Long getSendPeople(){
    return sendPeople;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

}