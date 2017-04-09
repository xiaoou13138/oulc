package com.ncu.table.engine;

import java.util.HashMap;
import java.util.List;

import com.ncu.table.bean.PictureBean;
import com.ncu.table.ivalue.IPictureValue;
import com.ncu.util.beanUtil.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PictureEngine{
	@Autowired
	BeanUtil beanUtil;
	public List<?> queryByCondition(String condition , HashMap<String,String> params,int beginPage ,int endPage) throws Exception{
		return beanUtil.queryByCondition(condition, params, beginPage, endPage, PictureBean.beanClass.getSimpleName());
	}
	public void save(IPictureValue value)throws Exception{
		beanUtil.save(value);
	}
}
