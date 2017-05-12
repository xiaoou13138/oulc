package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IResourceSV;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/5/10.
 */
@Controller
@Scope("prototype")
public class OrgShowController extends BaseController {
    @Resource(name="ResourceSVImpl")
    private IResourceSV resourceSV;

    @RequestMapping(value="/orgShow")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("orgShow");
        mv.addObject("data",data);
        return mv;
    }

    @RequestMapping(value="/orgShow_dealAction",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object dealAction(){
        JSONObject rtnObject = this.getObject();
        String rtn = "Y";
        try{
            JSONObject viewObject = this.getViewJSON();
            int actionType = viewObject.getInt("actionType");
            if(actionType == 1){
                //获取组织的信息
                long orgId = viewObject.getLong("orgId");
                HashMap map = resourceSV.queryResourceByEntityIdAndType(orgId);
                rtnObject.putAll(map);
            }
        }catch (Exception e){
            rtn= "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }
}
