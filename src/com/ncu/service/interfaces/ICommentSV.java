package com.ncu.service.interfaces;

import com.ncu.table.ivalue.ICommentValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoou on 2017/3/29.
 */
public interface ICommentSV {
    /**
     * 保存评论信息
     * @param value
     */
    public void save(ICommentValue value) throws Exception;

    /**
     * 根据网页的ID查询评论的信息
     * @return
     * @throws Exception
     */
    public List<ICommentValue> queryWebCommentById(String webId,int begin,int end) throws Exception;


    /**
     * 根据玩个的ID查询网页的信息然后转成Map返回去
     * @param webId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public List<Map> getCommentByWebId(String webId, int begin, int end) throws Exception;

    /**
     * 根据页面传进来的信息保存评论的信息
     * @param userId
     * @param content
     * @param webId
     * @throws Exception
     */
    public void saveContentByViewData(String userId,String content,String webId) throws Exception;

}
