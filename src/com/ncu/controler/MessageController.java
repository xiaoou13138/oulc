package com.ncu.controler;

import com.ncu.cache.StaticDataCache;
import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IFriendSV;
import com.ncu.service.interfaces.IMessageSV;
import com.ncu.table.bean.MessageBean;
import com.ncu.table.ivalue.IMessageValue;
import com.ncu.table.ivalue.IStaticDataValue;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 私信页面的控制器
 * Created by xiaoou on 2017/3/27.
 */
@Controller
@Scope("prototype")
public class MessageController extends BaseController{
    //private
    @Autowired
    private StaticDataCache staticDataCache;
    @Autowired
    @Qualifier("FriendSVImpl")
    private IFriendSV friendSV;

    @Autowired
    @Qualifier("MessageSVImpl")
    private IMessageSV messageSV;


    /**
     * 用户请求网页
     * @return
     */
    @RequestMapping(value="/message")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("message");
        mv.addObject("data",data);
        return mv;
    }

    /**
     * 页面初始化调用的方法
     * @return
     */
    @RequestMapping(value="/message_getBaseInfo" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getBaseInfo(){
        JSONObject rtnObject = this.getObject();
        //获取提示标签的信息 begin
        List<IStaticDataValue> list= staticDataCache.getStaticDataByCode("promptingMessage");
        if(list != null && list.size()>0){
            rtnObject.put("promptingMessage",list.get(0).getCodeValue());
        }
        //获取提示标签的信息 end
        try{
            ViewData viewData = this.getViewData();
            //获取右边的好友的信息 begin
            long userId = getLongParamFromSession("userId");
            List friendInfo  = friendSV.getFrienInfoReturn(userId);//查询到用户的朋友的信息
            rtnObject.put("friendsName",friendInfo);

            //获取用户之间的聊天信息
            if(friendInfo.size()>0){
                HashMap map = (HashMap)friendInfo.get(0);
                long friendId = (long)map.get("userId");
                List messageList = messageSV.getMessageByUserId(userId,friendId,-1,-1);
                rtnObject.put("messageList",messageList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //获取右边最近聊天的好友的信息  暂时不做


        //获取已经点击的好友的聊天的私信的信息 begin
        //先把默认的激活的好友的信息获取到
        //获取已经点击的好友的聊天的私信的信息 end
        rtnObject.put("result","success");
        return  rtnObject;
    }

    /**
     * 用户点击好友的时候后台调用的方法
     * @return
     */
    @RequestMapping(value="/message_getMessage" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getMessage(){
        JSONObject rtnObject = this.getObject();
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            long  friendId = APPUtil.getSafeLongParamFromJSONObject(viewObject,"friendId");
            if(userId >0){
                List<MessageBean> messageList = messageSV.queryMessageByAcceptUserId(userId,friendId,-1,-1);
                rtnObject.put("messageList",messageList);
                return rtnObject;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtnObject;
    }

    @RequestMapping(value="/message_send" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object messageSend(){
        JSONObject rtnObject = this.getObject();
        String rtnStr = "N";
        try{
            ViewData viewData =this.getViewData();
            JSONObject object = viewData.getJSONObject("VIEWDATA");
            String content = APPUtil.getSafeStringFromJSONObject(object,"content");
            long userId = getLongParamFromSession("userId");
            long acceptUserId = APPUtil.getSafeLongParamFromJSONObject(object,"acceptUserId");
            if(StringUtils.isNotBlank(content) &&userId > 0 && acceptUserId > 0){
                messageSV.save(content,userId,acceptUserId);
                messageSV.saveChangeMessageNoticeQueue(acceptUserId,true);
                rtnStr="Y";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        rtnObject.put("result",rtnStr);
        return rtnObject;
    }
}
