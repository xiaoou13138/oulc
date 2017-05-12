package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IResourceSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/20.
 */
@Controller
@Scope("prototype")
public class ResourceController extends BaseController {
    @Resource(name="ResourceSVImpl")
    private IResourceSV resourceSV;

    @RequestMapping(value="/resource")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        JSONObject data = this.getObject();
        //先获取参数
        mv.setViewName("resource");
        mv.addObject("data",data);
        return mv;
    }

    /**
     * 保存信息
     * @return
     */
    @RequestMapping(value="/resource_action" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object action(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long userId = getUserId();
            if(userId <= 0){
                throw new Exception("请先登录");
            }
            int actionType = viewContent.getInt("actionType");
            if(actionType == 1){//新增
                resourceSV.saveResource(viewContent);
            }else if(actionType == 2){//修改时候的保存
                resourceSV.updateResource(viewContent);
            }else if(actionType == 3){//修改的时候获取数据
                long resourceId = viewContent.getLong("resourceId");
                HashMap map = resourceSV.queryResourceByIdForController(resourceId);
                rtnObject.putAll(map);
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
