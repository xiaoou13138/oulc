package com.ncu.service.interfaces;

import com.ncu.table.bean.MessageBean;
import com.ncu.table.ivalue.IMessageNoticeQueueValue;
import com.ncu.table.ivalue.IMessageValue;

import java.util.List;

/**
 * Created by xiaoou on 2017/3/23.
 */
public interface IMessageSV {
    /**
     * 根据跟接受人的ID查询是否有消息
     * @param userId 被接受人的ID
     * @return
     * @throws Exception
     */
    public IMessageNoticeQueueValue queryNoticeInfoByUserId(long userId) throws Exception;

    /**
     * 根据用户的ID查找所有聊天的信息  不管是发送人还是接收人 然后排序
     * @param userId
     * @return
     * @throws Exception
     */
    public List<MessageBean> queryMessageByAcceptUserId(long userId, long friendUserId, int beign, int end) throws  Exception;

    /**
     * 保存用户聊天的信息
     * @throws Exception
     */
    public void save( IMessageValue value)  throws Exception;

    /**
     * 根据用户的信息和朋友的ID获取他们之间的信息
     * @param userId
     * @param friendId
     * @return
     * @throws Exception
     */
    public  List getMessageByUserId(long userId,long friendId,int begin,int end) throws Exception;

    /**
     * 用户发送消息的时候的流程
     * @param content
     * @param sendUserId
     * @param acceptUserId
     * @throws Exception
     */
    public void save(String content,long sendUserId,long acceptUserId) throws Exception;

    /**
     * 根据add判断是要+1 还是-1
     * @param userId
     * @param add
     * @throws Exception
     */
    public void saveChangeMessageNoticeQueue(long userId,boolean add) throws Exception;

    /**
     * 根据用户的id查询用户是否有提醒的消息
     * @param userId
     * @return
     * @throws Exception
     */
    public List<IMessageNoticeQueueValue> queryMessageNoticeQueueByUserId(long userId) throws  Exception;

    public int queryMessageNoticeQueueNum(long userId)  throws Exception;
}
