package com.ncu.table.engine;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ncu.table.bean.CatalogEntityRelBean;
import com.ncu.util.beanUtil.BeanUtil;

@Repository
public class CatalogEntityRelEngine{
@Autowired
BeanUtil beanUtil;

  public List queryByCondition(String condition , HashMap<String,String> params,int beginPage ,int endPage) throws Exception{
    return beanUtil.queryByCondition(condition, params, beginPage, endPage, CatalogEntityRelBean.beanClass.getSimpleName());
  }
  public void save(Object value) throws Exception{
    beanUtil.save(value);
  }
  public long queryCountByCondition(String condition , HashMap<String,String> params)throws Exception{
    return beanUtil.getCount(condition,params,CatalogEntityRelBean.beanClass.getSimpleName());
  }
  public void delete (Object value)throws Exception{  
    beanUtil.delete(value);
  }
}
