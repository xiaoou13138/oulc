package com.ncu.controler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ncu.service.interfaces.IMessageSV;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.ivalue.IMessageNoticeQueueValue;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;

public class BaseController {
	@Autowired
	@Qualifier("MessageSVImpl")
	private IMessageSV messageSV;

	@Resource(name="UserSVImpl")
	private IUserSV userSV;

	private JSONObject baseJsonObject = new JSONObject();
	private ViewData returnViewData = new ViewData();
	private HttpSession session = null;
	private HttpServletRequest request = null;
	private long userId = 0;
	/**
	 * 初始化函数
	 */
	@PostConstruct
	public void init(){
		try{
			this.request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			this.session = this.request.getSession();

			String userName = getStringParamFromSession("userName");
			String userType = getStringParamFromSession("userType");
			userId =getLongParamFromSession("userId");

			int num = messageSV.queryMessageNoticeQueueNum(userId);
			baseJsonObject.put("messageNum",num);//提示您有N条私信
			returnViewData.put("messageNum",num);
			if(userId ==0 || StringUtils.isBlank(userName) || StringUtils.isBlank(userType)){
				Cookie[] cookies  = request.getCookies();
				HashMap map =userSV.checkUserInfoByCookie(cookies);
				userId = (long)map.get("userId");
				userName = (String)map.get("userName");
				userType = (String)map.get("userType");
				session.setAttribute("userId",userId);
				session.setAttribute("userName",userName);
				session.setAttribute("userType",userType);
			}
			returnViewData.put("userId",userId);
			returnViewData.put("userName",userName);
			returnViewData.put("userType",userType);
			baseJsonObject.put("userId",userId);
			baseJsonObject.put("userName",userName);
			baseJsonObject.put("userType",userType);

		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取request
	 * @return
	 */
	public HttpServletRequest getRequest() throws Exception{
		return this.request;
	}
	
	/**
	 * 获得界面传输过来的信息
	 * @return
	 */
	public ViewData getViewData() throws Exception{
		return new ViewData(this.getRequest());
	}
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	public ViewData getReturnViewData(){
		return returnViewData;
	}
	
	public JSONObject getObject(){
		return baseJsonObject;

	}
	public HttpSession getSession(){
		return this.session;
	}
	/**
	 * 从session里面获取long类型的数据  如果没有就返回0
	 * @param code
	 * @return
	 * @throws
	 */
	public long getLongParamFromSession(String code) throws Exception{
		Object value = session.getAttribute(code);
		if(value != null){
			return  (long)  value;
		}
		return 0;
	}

	/**
	 * 从session里面获取String类型的值 如果不存在就返回null
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getStringParamFromSession(String code)throws Exception{
		Object value = session.getAttribute(code);
		if(value != null){
			return  (String)  value;
		}
		return null;
	}

	public JSONObject getViewJSON() throws Exception{
		ViewData viewData = this.getViewData();
		return viewData.getJSONObject("VIEWDATA");
	}
	public long getUserId(){
		return this.userId;
	}
}
