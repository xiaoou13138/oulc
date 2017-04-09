package com.ncu.service.impl;

import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.dao.interfaces.IFriendDAO;
import com.ncu.service.interfaces.IFriendSV;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.ivalue.IFriendValue;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.SQLCon;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Created by xiaoou on 2017/3/27.
 */
@Service("FriendSVImpl")
public class FriendSVImpl implements IFriendSV{
    @Autowired
    @Qualifier("FriendDAOImpl")
    private IFriendDAO dao;
    @Autowired
    @Qualifier("CommonDAOImpl")
    private ICommonDAO commonDAO;
    @Autowired
    @Qualifier("UserSVImpl")
    private IUserSV userSV;

    /**
     * 查询用户好友的信息
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<IUserValue> queryFriendInfoByUserId(long userId) throws Exception {
        StringBuilder condition1 = new StringBuilder();
        HashMap params1 = new HashMap();
        SQLCon.connectSQL(IFriendValue.S_UserId,userId,condition1,params1,false);
        List<IFriendValue> list1 = dao.queryInfoByCodition(condition1.toString(),params1,-1,-1);

        StringBuilder condition2 = new StringBuilder();
        HashMap params2 = new HashMap();
        SQLCon.connectSQL(IFriendValue.S_FriendUserId,userId,condition2,params2,false);
        List<IFriendValue> list2 = dao.queryInfoByCodition(condition2.toString(),params2,-1,-1);

        ArrayList friendList = new ArrayList();
        for(int i = 0;i<list1.size();i++){
            friendList.add(list1.get(i).getFriendUserId());
        }
        for(int i = 0;i<list2.size();i++){
            friendList.add(list2.get(i).getUserId());
        }
        return userSV.queryUserInfoByUserIds(friendList);
    }

    /**
     * 保存用户的好友信息
     * @param value
     * @throws Exception
     */
    @Override
    public void save(IFriendValue value) throws Exception {
        dao.save(value);
    }

    /**
     * 查询用户的朋友的信息返回到controller
     * @param userId 用户的主键
     * @return
     * @throws Exception
     */
    @Override
    public List getFrienInfoReturn(String userId) throws Exception {
        List rtnList = new ArrayList();//这个是要返回的朋友的信息
        if(StringUtils.isNotBlank(userId)){
            List<IUserValue> friendsList = queryFriendInfoByUserId(Long.parseLong(userId));//查询用户的朋友的所有信息
            if(friendsList != null){
                int length = friendsList.size();
                for(int i = 0;i<length;i++){
                    HashMap map = new HashMap();
                    map.put("userName",friendsList.get(i).getName());
                    map.put("userId",friendsList.get(i).getUserId());
                    rtnList.add(map);
                }
            }
        }
        return rtnList;
    }
}
