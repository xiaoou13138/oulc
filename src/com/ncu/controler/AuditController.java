package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IAuditSV;
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
 * Created by xiaoou on 2017/4/17.
 */
@Controller
@Scope("prototype")
public class AuditController extends BaseController{
    @Resource(name="AuditSVImpl")
    private IAuditSV auditSV;

    @RequestMapping(value="/audit")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("audit");
        mv.addObject("data",data);
        return mv;
    }
    @RequestMapping(value="/audit_getAuditInfo" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getAuditInfo(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long userId = getLongParamFromSession("userId");
            String searchContent = APPUtil.getSafeStringFromJSONObject(viewContent,"searchContent");
            int begin = viewContent.getInt("begin");
            int end = viewContent.getInt("end");
            HashMap map = auditSV.queryAuditInfoBySearchContent(searchContent,begin,end);
            rtnObject.putAll(map);
        }catch (Exception e){
            rtn = "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }

    @RequestMapping(value="/audit_changeState" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object changeState(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long actionType = APPUtil.getSafeLongParamFromJSONObject(viewContent,"actionType");
            long auditId = APPUtil.getSafeLongParamFromJSONObject(viewContent,"auditId");
            if(actionType == 2 || actionType == 3){
                auditSV.dealAudit(auditId,actionType);
            }
            if(auditId < 0){
                throw new Exception("审核的数据不准确");
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
