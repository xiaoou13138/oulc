package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.ICommentValue;

@Entity
@Table(name ="comment")
public class CommentBean implements ICommentValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ICommentValue.S_Id)
  private Long id;

  @Column(name = ICommentValue.S_UserId)
  private Long userId;

  @Column(name = ICommentValue.S_Content)
  private String content;

  @Column(name = ICommentValue.S_EntityId)
  private Long entityId;

  @Column(name = ICommentValue.S_EntityType)
  private String entityType;

  @Column(name = ICommentValue.S_CreateDate)
  private Date createDate;

  @Column(name = ICommentValue.S_DelFlag)
  private Long delFlag;

  @Column(name = ICommentValue.S_ModifyDate)
  private Date modifyDate;

  @Transient
  public static Class beanClass = CommentBean.class;

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

  public void setContent(String value){
    this.content = value;
  }

  public String getContent(){
    return content;
  }

  public void setEntityId(Long value){
    this.entityId = value;
  }

  public Long getEntityId(){
    return entityId;
  }

  public void setEntityType(String value){
    this.entityType = value;
  }

  public String getEntityType(){
    return entityType;
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

  public void setModifyDate(Date value){
    this.modifyDate = value;
  }

  public Date getModifyDate(){
    return modifyDate;
  }

}