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
            ViewData viewData = this.getViewData();
            String realName = (String) viewData.get("realName");
            int a= 0;
            realName = this.getRequest().getParameter("realName");
            int b =2;
            rtnObject.put("result","Y");
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtnObject;
    }
}
