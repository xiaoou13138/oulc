package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IReportSV;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/5/11.
 */
@Controller
@Scope("prototype")
public class OpenAccountController extends BaseController {
    @Resource(name="UserSVImpl")
    private IUserSV userSV;

    @Resource(name="ReportSVImpl")
    private IReportSV reportSV;

    @RequestMapping(value="/openAccount")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("openAccount");
        mv.addObject("data",data);
        return mv;
    }

    @RequestMapping(value="/openAccount_dealAction" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object dealAction(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long userId = getLongParamFromSession("userId");
            int actionType = viewContent.getInt("actionType");
            if(actionType == 1){//查询开放账号
                int begin = viewContent.getInt("begin");
                int end = viewContent.getInt("end");
                String searchContent = viewContent.getString("searchContent");
                HashMap map = userSV.queryUserInfoByConditionForController(searchContent,begin,end);
                rtnObject.putAll(map);
            }else if(actionType == 2){//举报开放账号
                if(userId <=0){
                    throw new Exception("用户请先登录");
                }
                String content = viewContent.getString("content");
                long reportUserId = viewContent.getLong("userId");
                reportSV.saveReport(content,userId,reportUserId,1L);//1是用户
            }
        }catch (Exception e){
            rtn = "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }
}
