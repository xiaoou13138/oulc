package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IWebInfoValue;

@Entity
@Table(name ="web_info")
public class WebInfoBean implements IWebInfoValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IWebInfoValue.S_Id)
  private Long id;

  @Column(name = IWebInfoValue.S_Title)
  private String title;

  @Column(name = IWebInfoValue.S_HeadContent)
  private String headContent;

  @Column(name = IWebInfoValue.S_Url)
  private String url;

  @Column(name = IWebInfoValue.S_DelFlag)
  private Long delFlag;

  @Column(name = IWebInfoValue.S_CreateDate)
  private Date createDate;

  @Column(name = IWebInfoValue.S_OperId)
  private Long operId;

  @Transient
  public static Class beanClass = WebInfoBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setId(Long value){
    this.id = value;
  }

  public Long getId(){
    return id;
  }

  public void setTitle(String value){
    this.title = value;
  }

  public String getTitle(){
    return title;
  }

  public void setHeadContent(String value){
    this.headContent = value;
  }

  public String getHeadContent(){
    return headContent;
  }

  public void setUrl(String value){
    this.url = value;
  }

  public String getUrl(){
    return url;
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

  public void setOperId(Long value){
    this.operId = value;
  }

  public Long getOperId(){
    return operId;
  }

}