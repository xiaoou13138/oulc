package com.ncu.service.impl;

import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.dao.interfaces.IMessageDAO;
import com.ncu.dao.interfaces.IMessageNoticeQueueDAO;
import com.ncu.service.interfaces.IMessageSV;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.bean.MessageBean;
import com.ncu.table.bean.MessageNoticeQueueBean;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.table.ivalue.IMessageNoticeQueueValue;
import com.ncu.table.ivalue.IMessageValue;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/3/23.
 */
@Service("MessageSVImpl")
public class MessageSVImpl implements IMessageSV {
    @Autowired
    @Qualifier("MessageDAOImpl")
    private IMessageDAO dao;
    @Autowired
    @Qualifier("CommonDAOImpl")
    ICommonDAO comDao;
    @Autowired
    @Qualifier("MessageNoticeQueueDAOImpl")
    private IMessageNoticeQueueDAO messageNoticeQueueDAO;

    @Autowired
    @Qualifier("UserSVImpl")
    private IUserSV userSV;


    /**
     * 根据用户的ID产用户是否有被私信的信息 有一张通知表
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public IMessageNoticeQueueValue queryNoticeInfoByUserId(long userId) throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IMessageNoticeQueueValue.S_UserId,String.valueOf(userId),condition,params,false);
        List<IMessageNoticeQueueValue> list = messageNoticeQueueDAO.queryInfoByCondition(condition.toString(),params,-1,-1);
        if(list != null && list.size() ==1){
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据用户的ID查找所有聊天的信息  不管是发送人还是接收人 然后排序
     * @param userIdSmall
     * @param userIdBig
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    @Override
    public List<MessageBean> queryMessageByAcceptUserId(long userIdSmall,long userIdBig,int begin,int end) throws Exception {
        String sql = "";
        if(begin !=-1 && end != -1){
            sql ="from MessageBean a where a.userIdSmall=:userIdSmall and a.userIdBig=:userIdBig order by id  limit "+begin+","+end;
        }else{
            sql ="from MessageBean a where a.userIdSmall=:userIdSmall and a.userIdBig=:userIdBig order by id  ";
        }

        List<ParamsDefine> list = new ArrayList();
        ParamsDefine paramsDefine = new ParamsDefine();
        paramsDefine.setColName("userIdSmall");
        paramsDefine.setIsList(false);
        paramsDefine.setParamVal(userIdSmall);
        list.add(paramsDefine);

        ParamsDefine paramsDefine1 = new ParamsDefine();
        paramsDefine1.setColName("userIdBig");
        paramsDefine1.setIsList(false);
        paramsDefine1.setParamVal(userIdBig);
        list.add(paramsDefine1);
        List <MessageBean> rtnlList = comDao.commonQuery(sql,list.toArray(new ParamsDefine[0]));
        if(rtnlList != null){
            return rtnlList;
        }
        return new ArrayList<MessageBean>();
    }

    @Override
    public void save(IMessageValue value) throws Exception {

    }
    /**
     * 根据用户的信息和朋友的ID获取他们之间的信息
     * @param userId
     * @param friendId
     * @return
     * @throws Exception
     */

    public  List getMessageByUserId(String userId,String friendId,int begin,int end) throws Exception{
        List rtnList = new ArrayList();
        if(StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(friendId)){
            //查询两个人的名称
            long smallUserId = Long.parseLong(userId);
            long bigUserId = Long.parseLong(friendId);
            String smallUserIdName = "";
            String bigUserIdName = "";
            if(smallUserId>bigUserId) {
                long t = smallUserId;
                smallUserId = bigUserId;
                bigUserId = t;
            }
            ArrayList userIdList = new ArrayList();
            userIdList.add(smallUserId);
            userIdList.add(bigUserId);
            List<IUserValue> userList = userSV.queryUserInfoByUserIds(userIdList);
            if(userList.size()!=2){
                throw new Exception("根据两个userId查不到两个用户的信息");
            }else{
                if(userList.get(0).getUserId() == smallUserId){
                    smallUserIdName = userList.get(0).getName();
                    bigUserIdName = userList.get(1).getName();
                }else{
                    bigUserIdName = userList.get(0).getName();
                    smallUserIdName = userList.get(1).getName();
                }
            }
            List<MessageBean> list = queryMessageByAcceptUserId(smallUserId,bigUserId,-1,-1);
            if(list != null){
                int length = list.size();
                for(int i =0 ;i<length;i++){
                    HashMap map = new HashMap();
                    map.put("userIdSmall",list.get(i).getUserIdSmall());
                    map.put("smallUserIdName",smallUserIdName);
                    map.put("userIdBig",list.get(i).getUserIdBig());
                    map.put("bigUserIdName",bigUserIdName);
                    map.put("content",list.get(i).getContent());
                    map.put("sendPeople",list.get(i).getSendPeople());
                    map.put("time",TimeUtil.formatTimeyyyyMMddhhmmss(list.get(i).getCreateDate()));
                    rtnList.add(map);
                }
            }
        }
        return rtnList;
    }

    /**
     * 保存用户发送的信息，然后推送给另外一个人
     * @param content
     * @param sendUserId
     * @param acceptUserId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(String content, long sendUserId, long acceptUserId) throws Exception {
        if(StringUtils.isNotBlank(content) && sendUserId !=0 && acceptUserId !=0){
            long smallUserId =0;
            long bigUserId =0;
            int send_people = -1;
            if(sendUserId<acceptUserId){
                smallUserId = sendUserId;
                bigUserId = acceptUserId;
                send_people = 0;
            }else{
                smallUserId = acceptUserId;
                bigUserId = sendUserId;
                send_people = 1;
            }
            MessageBean bean = new MessageBean();
            bean.setContent(content);
            bean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
            bean.setUserIdBig(bigUserId);
            bean.setUserIdSmall(smallUserId);
            bean.setSendPeople((long)send_people);
            dao.save(bean);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveChangeMessageNoticeQueue(long userId,boolean add) throws Exception{
        List<IMessageNoticeQueueValue> list = queryMessageNoticeQueueByUserId(userId);
        if(list !=null && list.size()>0){
            IMessageNoticeQueueValue value = list.get(0);
            if(add){
                value.setMessageNum(value.getMessageNum()+1);
                messageNoticeQueueDAO.save(value);
            }else{
                value.setMessageNum(0L);
                messageNoticeQueueDAO.save(value);
            }
        }else{
            if(add){
                MessageNoticeQueueBean bean = new MessageNoticeQueueBean();
                bean.setMessageNum(1L);
                bean.setUserId(userId);
                messageNoticeQueueDAO.save(bean);
            }
        }
    }



    /**
     * 根据用户的ID查询通知表的信息
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<IMessageNoticeQueueValue> queryMessageNoticeQueueByUserId(long userId) throws Exception {
        if(userId !=0){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(IMessageNoticeQueueValue.S_UserId,userId,condition,params,false);
            return messageNoticeQueueDAO.queryInfoByCondition(condition.toString(),params,-1,-1);
        }
        return null;
    }

    @Override
    public int queryMessageNoticeQueueNum(long userId) throws Exception {
        long num = 0;
        if(userId !=0){
            List<IMessageNoticeQueueValue> list = queryMessageNoticeQueueByUserId(userId);
            if(list != null && list.size()>0){
                num = list.get(0).getMessageNum();
            }
        }
        return (int)num;
    }
}
