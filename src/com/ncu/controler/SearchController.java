package com.ncu.controler;

import javax.annotation.Resource;

import com.ncu.table.ivalue.IWebInfoValue;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.ISearchInfoSV;

import java.util.List;

@Controller
@Scope("prototype")
public class SearchController extends BaseController{
	public static Logger log = Logger.getLogger(LoginController.class);
	
	@Resource(name="SearchInfoSVImpl")
	private ISearchInfoSV searchInfoSV;
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = this.getReturnViewData();
		mv.setViewName("main");
		mv.addObject("data",data);
		return mv;
	}


	/**
	 * 用户查询时调的方法
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search_doSearch",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object doSearch() throws Exception{
		JSONObject rtnObject = this.getObject();
		ViewData viewData = this.getViewData();
		JSONObject data = viewData.getJSONObject("VIEWDATA");
		List webInfoIdList = null;//查找到的网站的ID
		JSONArray array = new JSONArray();//返回网页元素
		if(data.containsKey("searchParams")) {
			JSONArray paramsArray = data.getJSONArray("searchParams");
			array = searchInfoSV.getHtmlByParams(paramsArray,paramsArray.size(),-1,-1);
		}


		rtnObject.put("result","success");
		rtnObject.put("html",array);
		return rtnObject;

	}
	
	
	
}
