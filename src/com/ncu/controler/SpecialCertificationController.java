package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IApplySV;
import com.ncu.service.interfaces.IOrgDefSV;
import com.ncu.table.ivalue.IOrgDefValue;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by xiaoou on 2017/4/16.
 */
@Controller
@Scope("prototype")
public class SpecialCertificationController extends BaseController {
    @Resource(name="ApplySVImpl")
    private IApplySV applySV;
    @Resource(name="OrgDefSVImpl")
    private IOrgDefSV orgDefSV;

    /**
     * 用户请求网页
     * @return
     */
    @RequestMapping(value="/specialCertification")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        boolean hasCertificate = true;
        //查询用户是否已经认证过
        long userId = this.getUserId();
        if(userId > 0){
            IOrgDefValue orgDefValue = orgDefSV.queryOrgDefByUserId(userId);
            if(orgDefValue != null){
                hasCertificate =false;
            }
        }
        data.put("hasCertificate",hasCertificate);
        mv.setViewName("specialCertification");
        mv.addObject("data",data);
        return mv;
    }
    @RequestMapping(value="/certification_saveCertificateInfo" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object saveInfo(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long userId = getLongParamFromSession("userId");
            applySV.saveApplyInfoByJSONObject(viewContent,userId);
        }catch (Exception e){
            rtn = "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }
}
