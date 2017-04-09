package com.ncu.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncu.cache.StaticDataCache;
import com.ncu.service.interfaces.IUploadPictureSV;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
@Controller
@Scope("prototype")
public class UpLoadHouseInfoController extends BaseController{
	
	public static Logger log = Logger.getLogger(UpLoadHouseInfoController.class);
	@Autowired
	private StaticDataCache cache;
	@Autowired
	@Qualifier("UploadPictureSVImpl")
	private IUploadPictureSV uploadPictureSV;
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/upLoadHouseInfo")
	public ModelAndView choiceRegist()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = new ViewData();
		mv.setViewName("upLoadHouseInfo");
		mv.addObject("data",data);
		return mv;
	} 
	/**
     * 用户上传文件
     * @param pojo
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value="/uploadImage")
	@ResponseBody
	public void fileUpload(@RequestParam ("file") MultipartFile fileUpload,HttpServletRequest request,HttpServletResponse response){
		String path = cache.getStaticDataByCode("physicalPath").get(0).getCodeValue();
		try{
			uploadPictureSV.saveImageByUploadPicture(fileUpload);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	     
	
	

}
