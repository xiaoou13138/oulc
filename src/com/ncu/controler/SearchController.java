package com.ncu.controler;

import javax.annotation.Resource;

import com.ncu.table.ivalue.IWebInfoValue;
import com.ncu.util.APPUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.ISearchInfoSV;

import java.util.HashMap;
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
	public ModelAndView getMainView()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = this.getReturnViewData();
		mv.setViewName("main");
		mv.addObject("data",data);
		return mv;
	}

	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/search")
	public ModelAndView getSearchView()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = this.getReturnViewData();
		mv.setViewName("search");
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
		String rtn = "Y";
		JSONObject rtnObject = this.getObject();
		try {
			JSONObject viewObject = this.getViewJSON();
			String searchContent = viewObject.getString("searchContent");
			int begin = viewObject.getInt("begin");
			int end = viewObject.getInt("end");
			if(StringUtils.isNotBlank(searchContent)){
				HashMap map = searchInfoSV.queryWebInfo(searchContent,begin,end);
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

	@RequestMapping(value="/search_dealAction",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object dealAction() throws Exception{
		String rtn = "Y";
		JSONObject rtnObject = this.getObject();
		try {
			JSONObject viewObject = this.getViewJSON();
			String url = viewObject.getString("url");
			String searchContent = viewObject.getString("searchContent");
			HashMap map = searchInfoSV.queryWebContent(url,searchContent);
			rtnObject.putAll(map);
		}catch (Exception e){
			rtn = "N";
			rtnObject.put("errMessage",e.getMessage());
			e.printStackTrace();
		}
		rtnObject.put("result",rtn);
		return rtnObject;
	}
	
	
	
}
