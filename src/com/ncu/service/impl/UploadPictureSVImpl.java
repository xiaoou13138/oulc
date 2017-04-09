package com.ncu.service.impl;

import java.io.File;
import java.util.Date;

import com.ncu.cache.StaticDataCache;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ncu.service.interfaces.IUploadPictureSV;
import com.ncu.util.TimeUtil;
@Service("UploadPictureSVImpl")
@Scope("prototype")
public class UploadPictureSVImpl implements IUploadPictureSV{
	@Autowired
	private StaticDataCache	 cache;
	
	private final static Logger log = Logger.getLogger(UploadPictureSVImpl.class);
	/**
	 * 根据上传上来的数据保存成图片
	 * @param file 图片
	 * @return
	 */
	@Override
	public boolean saveImageByUploadPicture(MultipartFile file) throws Exception{
		try {
			String fileName = getFileName(file);
			String filePath = cache.getStaticDataByCode("physicalPath").get(0).getCodeValue()+fileName;
			File imageFile = new File(filePath);
			if(!imageFile.exists()){
				imageFile.createNewFile();
			}else{
				throw new Exception(fileName+"文件已经存在!");
			}
			file.transferTo(imageFile);
			imageFile.createNewFile();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 获取文件的名称
	 * @return
	 */
	@Override
	public String getFileName(MultipartFile file) throws Exception {
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		int allow = cache.getStaticDataByCode("allowSuffix").get(0).getCodeValue().indexOf(suffix);
		if(allow == -1 ){
			throw new Exception("******上传上来的"+file.getOriginalFilename()+"是不符合的文件******");
		}else{
			String date = TimeUtil.getCurrentTimeyyyyMMddhhmmssStr();
			return date+"."+suffix;
		}
		
	}

}
