package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.ICommentSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
/**
 * Created by xiaoou on 2017/3/29.
 */
@Controller
@Scope("prototype")
public class CommentController extends BaseController {
    @Autowired
    @Qualifier("CommentSVImpl")
    private ICommentSV commentSV;

    @RequestMapping(value="/comment")
    public ModelAndView toLogin()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("comment");
        mv.addObject("data",data);
        return mv;
    }

    /**
     * 页面初始化的时候调的函数
     * @return
     */
    @RequestMapping(value="/comment_getComment",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getComment(){
        JSONObject rtnObject = this.getObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject = viewData.getJSONObject("VIEWDATA");
            long webId = APPUtil.getSafeLongParamFromJSONObject(viewObject,"webId");
            List commentList =commentSV.getCommentByWebId(webId,-1,-1);
            rtnObject.put("commentList",commentList);
        }catch (Exception e){
            rtn= "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }


    @RequestMapping(value="/comment_sendComment",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object sendComment(){
        JSONObject rtnObject= this.getObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("VIEWDATA");
            String content = APPUtil.getSafeStringFromJSONObject(viewObject,"content");
            String webId = APPUtil.getSafeStringFromJSONObject(viewObject,"webId");
            long userId = getLongParamFromSession("userId");
            commentSV.saveContentByViewData(userId,content,webId);
        }catch (Exception e){
            rtn= "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }
}
