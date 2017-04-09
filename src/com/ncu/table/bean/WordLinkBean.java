package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*;import java.util.Date;
import com.ncu.table.ivalue.IWordLinkValue;

@Entity
@Table(name ="word_link")
public class WordLinkBean implements IWordLinkValue{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IWordLinkValue.S_RelId)
  private int relId;

  @Column(name = IWordLinkValue.S_WordId)
  private int wordId;

  @Column(name = IWordLinkValue.S_WebInfoId)
  private int webInfoId;

  @Column(name = IWordLinkValue.S_DelFlag)
  private int delFlag;

  @Column(name = IWordLinkValue.S_CreateDate)
  private Date createDate;

  @Column(name = IWordLinkValue.S_ModifyDate)
  private Date modifyDate;

  @Transient
  public static Class beanClass = WordLinkBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setRelId(int value){
    this.relId = value;
  }

  public int getRelId(){
    return relId;
  }

  public void setWordId(int value){
    this.wordId = value;
  }

  public int getWordId(){
    return wordId;
  }

  public void setWebInfoId(int value){
    this.webInfoId = value;
  }

  public int getWebInfoId(){
    return webInfoId;
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