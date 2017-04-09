package com.ncu.controler;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IUserSV;
@Controller
@Scope("prototype")
public class LoginController extends BaseController{
	@Resource(name="UserSVImpl")
	private IUserSV sv;
	public static Logger log = Logger.getLogger(LoginController.class);
	
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/login")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = getReturnViewData();
		mv.setViewName("/login");
		mv.addObject("data",data);
		return mv;
	}
    
    /**
     * 用户提交登录
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login_check" ,produces="application/json;charset=UTF-8")
	@ResponseBody
    public Object checkPassword() throws Exception{
    	//判断用户的密码是否正确
		JSONObject rtnObject = this.getObject();
    	String rtn = "N";
    	ViewData data = this.getViewData();
    	JSONObject userInfo = (JSONObject)data.get("VIEWDATA");
		String code = APPUtil.getSafeStringFromJSONObject(userInfo,"code");
		String password = APPUtil.getSafeStringFromJSONObject(userInfo,"password");
		HashMap map = sv.checkUserInfo(code,password);
		this.getRequest().getSession().setAttribute("userId",map.get("userId"));
		this.getRequest().getSession().setAttribute("userName",map.get("userName"));
		if((boolean)map.get("result")){
			rtn = "Y";
		}
    	rtnObject.put("result", rtn);
    	return rtnObject;
    }
}
