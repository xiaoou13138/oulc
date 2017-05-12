package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IOrgUserRelValue;

@Entity
@Table(name ="org_user_rel")
public class OrgUserRelBean implements IOrgUserRelValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IOrgUserRelValue.S_RelId)
  private Long relId;

  @Column(name = IOrgUserRelValue.S_OrgId)
  private Long orgId;

  @Column(name = IOrgUserRelValue.S_UserId)
  private Long userId;

  @Column(name = IOrgUserRelValue.S_CreateDate)
  private Date createDate;

  @Transient
  public static Class beanClass = OrgUserRelBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setRelId(Long value){
    this.relId = value;
  }

  public Long getRelId(){
    return relId;
  }

  public void setOrgId(Long value){
    this.orgId = value;
  }

  public Long getOrgId(){
    return orgId;
  }

  public void setUserId(Long value){
    this.userId = value;
  }

  public Long getUserId(){
    return userId;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

}