package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.ICatalogSV;
import com.ncu.service.interfaces.IResourceSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/17.
 */
@Controller
@Scope("prototype")
public class OrgResourceManageController extends BaseController {
    @Resource(name="CatalogSVImpl")
    private ICatalogSV catalogSV;

    @Resource(name="ResourceSVImpl")
    private IResourceSV resourceSV;

    @RequestMapping(value="/orgResourceManage")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("orgResourceManage");
        mv.addObject("data",data);
        return mv;
    }

    @RequestMapping(value="/orgResourceManage_catalogAction" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object catalogActionDeal(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long userId = getUserId();
            if(userId <= 0){
                throw new Exception("用户主键错误 userId:"+userId);
            }
            int actionType = viewContent.getInt("actionType");
            long catalogId = viewContent.getLong("catalogId");
            long upCatalogId = viewContent.getLong("upCatalogId");
            long leval = viewContent.getLong("leval");
            if(actionType == 1){
                //新增同级目录
                String catalogName = viewContent.getString("catalogName");
                catalogSV.addCatalog(catalogName,leval,upCatalogId,catalogId,actionType);
            }else if(actionType == 2){
                String catalogName = viewContent.getString("catalogName");
                catalogSV.addCatalog(catalogName,leval,upCatalogId,catalogId,actionType);
                //新增下级目录
            }else if(actionType == 3){
                //删除目录
                catalogSV.delCatalog(catalogId);
            }
        }catch (Exception e){
            rtn = "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }

    @RequestMapping(value="/orgResourceManage_getCatalogData" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getCatalogData(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long userId = getUserId();
            if(userId <= 0){
                throw new Exception("用户主键错误 userId:"+userId);
            }
            JSONObject json = catalogSV.getTreeInfo(userId);
            rtnObject.putAll(json);
        }catch (Exception e){
            rtn = "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }

    @RequestMapping(value="/orgResourceManage_getResource" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getResource(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long userId = getUserId();
            if(userId <= 0){
                throw new Exception("用户主键错误 userId:"+userId);
            }
            long catalogId = viewContent.getLong("catalogId");
            int begin = viewContent.getInt("begin");
            int end = viewContent.getInt("end");
            HashMap map = resourceSV.queryResourceByCatalogIdForController(catalogId,begin,end);
            rtnObject.putAll(map);
        }catch (Exception e){
            rtn = "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }


    @RequestMapping(value="/orgResourceManage_dealAction" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object dealAction(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long userId = getUserId();
            if(userId <= 0){
                throw new Exception("用户主键错误 userId:"+userId);
            }
            int actionType =  viewContent.getInt("actionType");
            if(actionType == 1){

            }else if(actionType == 2){//删除资源
                JSONArray resourceArray = viewContent.getJSONArray("array");
                resourceSV.deleteResource(resourceArray,userId);
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
