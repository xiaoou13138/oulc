package com.ncu.controler;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * 修改用户信息的控制器
 * Created by xiaoou on 2017/3/28.
 */
@Controller
@Scope("prototype")
public class EditUserInfoController extends BaseController {
    @Autowired
    @Qualifier("UserSVImpl")
    private IUserSV userSV;

    /**
     * 用户请求网页
     * @return
     */
    @RequestMapping(value="/editUserInfo")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("editUserInfo");
        mv.addObject("data",data);
        return mv;
    }
    /**
     * 修改用户信息页面初始化时获得数据
     * @return
     */
    @RequestMapping(value="/editUserInfo_getEditUserInfo" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object viewInitData(){
        JSONObject rtnObject = this.getObject();
        try{
            long userId = getLongParamFromSession("userId");
            HashMap map = userSV.getEditViewInitData(userId);
            rtnObject.putAll(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtnObject;
    }
    @RequestMapping(value="/editUserInfo_editUserInfo" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object editUserInfo(){
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewData = this.getViewJSON();
            long userId = getLongParamFromSession("userId");
            userSV.updateUserInfo(viewData,userId);
            rtnObject.put("result","Y");
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtnObject;
    }
}
