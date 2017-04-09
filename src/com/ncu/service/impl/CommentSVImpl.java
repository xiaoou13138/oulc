package com.ncu.service.impl;

import com.ncu.dao.interfaces.ICommentDAO;
import com.ncu.service.interfaces.ICommentSV;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.bean.CommentBean;
import com.ncu.table.ivalue.ICommentValue;
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
import java.util.Map;

/**
 * Created by xiaoou on 2017/3/29.
 */
@Service("CommentSVImpl")
public class CommentSVImpl implements ICommentSV {
    @Autowired
    @Qualifier("CommentDAOImpl")
    private ICommentDAO commentDAO;
    @Autowired
    @Qualifier("UserSVImpl")
    private IUserSV userSV;

    /**
     * 根据网页的主键查询评论的信息
     * @param webId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    @Override
    public List<ICommentValue> queryWebCommentById(String webId,int begin,int end) throws Exception {
        if(StringUtils.isNotBlank(webId)){
            StringBuilder condition = new StringBuilder();
            HashMap params = new HashMap();
            SQLCon.connectSQL(ICommentValue.S_EntityId,webId,condition,params,false);
            SQLCon.connectSQL(ICommentValue.S_EntityType,"WEB",condition,params,false);
            SQLCon.connectSQL(ICommentValue.S_DelFlag,"1",condition,params,false);
            List <ICommentValue> list = commentDAO.queryCommentInfoByCondition(condition.toString(),params, begin, end);
            if(list != null && list.size()>0){
                return list;
            }
        }
        return null;
    }

    /**
     * 保存评论的信息
     * @param value
     * @throws Exception
     */
    @Override
    public void save(ICommentValue value) throws Exception {
        commentDAO.save(value);
    }

    public List<Map> getCommentByWebId(String webId, int begin, int end) throws Exception {
        ArrayList rtnList = new ArrayList();
        List <ICommentValue> list = queryWebCommentById(webId,begin,end);
        StringBuilder html = new StringBuilder();
        if(list != null){
            int length = list.size();
            for(int i =0;i<length;i++){
                ICommentValue value = list.get(i);
                String content = value.getContent();
                long userId = value.getUserId();
                IUserValue userValue =userSV.queryUserInfoByUserId(userId);
                if(value == null){
                    continue;
                }
                String userName = userValue.getName();
                HashMap map = new HashMap();
                map.put("content",content);
                map.put("userId",userId);
                map.put("userName",userName);
                rtnList.add(map);

            }
        }
        return rtnList;
    }

    /**
     * 根据页面传进来的信息保存评论的信息
     * @param userId
     * @param content
     * @param webId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveContentByViewData(String userId,String content,String webId) throws Exception{
        if(StringUtils.isNotBlank(userId)&&StringUtils.isNotBlank(content)&&StringUtils.isNotBlank(webId)){
            CommentBean bean = new CommentBean();
            bean.setUserId(Long.parseLong(userId));
            bean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
            bean.setContent(content);
            bean.setDelFlag(1L);
            bean.setEntityId(Long.parseLong(webId));
            bean.setEntityType("WEB");
            commentDAO.save(bean);
        }else{
            throw new Exception("发送评论失败,请尝试重新登录");
        }
    }
}
