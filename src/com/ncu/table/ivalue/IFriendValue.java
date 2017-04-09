package com.ncu.table.ivalue;

import java.util.Date;

public interface IFriendValue{
  public final static String S_UserId = "USER_ID";
  public final static String S_FriendUserId = "FRIEND_USER_ID";
  public final static String S_CreateDate = "CREATE_DATE";
  public void setUserId(Long value);
  public void setFriendUserId(Long value);
  public void setCreateDate(Date value);
  public Long getUserId();
  public Long getFriendUserId();
  public Date getCreateDate();
}