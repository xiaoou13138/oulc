package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*;import java.util.Date;
import com.ncu.table.ivalue.IWordValue;

@Entity
@Table(name ="word")
public class WordBean implements IWordValue{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IWordValue.S_WordId)
  private int wordId;

  @Column(name = IWordValue.S_Word)
  private String word;

  @Column(name = IWordValue.S_WordDesc)
  private String wordDesc;

  @Column(name = IWordValue.S_DelFlag)
  private int delFlag;

  @Column(name = IWordValue.S_CreateDate)
  private Date createDate;

  @Column(name = IWordValue.S_ModifyDate)
  private Date modifyDate;

  @Transient
  public static Class beanClass = WordBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setWordId(int value){
    this.wordId = value;
  }

  public int getWordId(){
    return wordId;
  }

  public void setWord(String value){
    this.word = value;
  }

  public String getWord(){
    return word;
  }

  public void setWordDesc(String value){
    this.wordDesc = value;
  }

  public String getWordDesc(){
    return wordDesc;
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