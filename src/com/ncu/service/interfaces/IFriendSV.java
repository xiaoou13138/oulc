package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IFriendValue;
import com.ncu.table.ivalue.IUserValue;

import java.util.List;
/**
 * Created by xiaoou on 2017/3/27.
 */
public interface IFriendSV {
    public void save(IFriendValue value) throws Exception;
    public List<IUserValue> queryFriendInfoByUserId(long userId) throws Exception;

    /**
     * 根据用户的主键返回用户的朋友的信息
     * @param userId
     * @return
     * @throws Exception
     */
    public List getFrienInfoReturn(String userId)throws Exception;

}
