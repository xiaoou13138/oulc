package com.ncu.controler;

import com.ncu.data.ViewData;
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
 * Created by xiaoou on 2017/5/12.
 */
@Controller
@Scope("prototype")
public class ChangePasswordController extends BaseController {
    @Resource(name="UserSVImpl")
    private IUserSV userSV;

    @RequestMapping(value="/changePassword")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("changePassword");
        mv.addObject("data",data);
        return mv;
    }

    @RequestMapping(value="/changePassword_updateInfo" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getAuditInfo(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long userId = getLongParamFromSession("userId");
            if(userId <=0){
                throw new Exception("用户请先登录");
            }
            String oldPassword = viewContent.getString("oldPassword");
            String newPassword = viewContent.getString("newPassword");
            userSV.updatePasswordByOldAndNewPassword(oldPassword,newPassword,userId);
        }catch (Exception e){
            rtn = "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }
}
