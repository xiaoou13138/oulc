package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.ILevalSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xiaoou on 2017/5/10.
 */
@Controller
@Scope("prototype")
public class LevalController extends BaseController {
    @Resource(name="LevalSVImpl")
    private ILevalSV levalSV;

    @RequestMapping(value="/leval")
    public ModelAndView toLogin()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("leval");
        mv.addObject("data",data);
        return mv;
    }

    @RequestMapping(value="/leval_saveInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object saveInfo(){
        JSONObject rtnObject = this.getObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject = this.getViewJSON();
            long userId = getLongParamFromSession("userId");
            if(userId <=0){
                throw new Exception("用户请先登录");
            }
            levalSV.saveLevalByJson(viewObject);
        }catch (Exception e){
            rtn= "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }
}
