package com.ncu.service.impl;

import com.ncu.dao.interfaces.ICatalogDAO;
import com.ncu.service.interfaces.ICatalogEntityRelSV;
import com.ncu.service.interfaces.ICatalogSV;
import com.ncu.service.interfaces.IOrgUserRelSV;
import com.ncu.table.bean.CatalogBean;
import com.ncu.table.ivalue.ICatalogEntityRelValue;
import com.ncu.table.ivalue.ICatalogValue;
import com.ncu.table.ivalue.IOrgUserRelValue;
import com.ncu.util.SQLCon;
import com.ncu.util.TimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
@Service("CatalogSVImpl")
public class CatalogSVImpl implements ICatalogSV{
    @Resource(name="CatalogDAOImpl")
    private ICatalogDAO catalogDAO;

    @Resource(name="OrgUserRelSVImpl")
    private IOrgUserRelSV orgUserRelSV;

    @Resource(name="CatalogEntityRelSVImpl")
    private ICatalogEntityRelSV catalogEntityRelSV;
    /**
     * 保存目录信息
     * @param value
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCatalog(ICatalogValue value)throws Exception{
        catalogDAO.save(value);
    }
    /**
     * 查找目录信息
     * @param userId 用户主键
     * @return
     * @throws Exception
     */
    public JSONObject getTreeInfo(long userId)throws Exception{
        JSONObject rtnJSON = new JSONObject();
        IOrgUserRelValue orgUserRelValue = orgUserRelSV.queryOrgUserRelByUserId(userId);
        if(orgUserRelValue == null){
            throw new Exception("用户没有认证为特殊账户");
        }
        long orgId = orgUserRelValue.getOrgId();

        //查找一级目录的信息
        List<ICatalogEntityRelValue> catalogEntityRelValueList = catalogEntityRelSV.queryCatalogEntityRelByEntityIdAndType(orgId,1L);
        if(catalogEntityRelValueList == null || catalogEntityRelValueList.size() == 0){
            throw new Exception("组织没有找到关联的一级目录");
        }
        ICatalogEntityRelValue catalogEntityRelValue = catalogEntityRelValueList.get(0);
        long catalogId = catalogEntityRelValue.getCatalogId();//获取到一级目录的主键
        List<ICatalogValue> catalogValueList =  queryCatalogByCatalogId(catalogId);
        putData(catalogValueList,rtnJSON);
        return rtnJSON;
    }
    /**
     * 创建目录容器
     * @param list
     * @param json
     * @throws Exception
     */
    public void putData(List<ICatalogValue> list,JSONObject json)throws Exception{
        JSONArray jsonArray = new JSONArray();
        for(int i = 0;i<list.size();i++){
            ICatalogValue catalogValue = list.get(i);
            long catalogId = catalogValue.getCatalogId();
            JSONObject nodeJSON = new JSONObject();
            nodeJSON.put("text",catalogValue.getCatalogName());
            nodeJSON.put("catalogId",catalogId);
            nodeJSON.put("leval",catalogValue.getLeval());
            nodeJSON.put("upCatalogId",catalogValue.getUpCatalogId());
            List<ICatalogValue> dList =queryCatalogByUpCatalogId(catalogId);
            if(dList != null && dList.size()>0){
                putData(dList,nodeJSON);
            }
            jsonArray.add(nodeJSON);
        }
        json.put("nodes",jsonArray);
    }

    /**
     * 查询目录的信息
     * @param catalogId 目录主键
     * @return
     * @throws Exception
     */
    public List<ICatalogValue> queryCatalogByCatalogId(long catalogId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(ICatalogValue.S_CatalogId,catalogId,condition,params,false);
        SQLCon.connectSQL(ICatalogValue.S_DelFlag,1L,condition,params,false);
        return  catalogDAO.queryCatalogByCondition(condition.toString(),params,-1,-1);
    }

    /**
     * 查询目录信息
     * @param upCatalogId 上级目录主键
     * @return
     * @throws Exception
     */
    public List<ICatalogValue> queryCatalogByUpCatalogId(long upCatalogId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(ICatalogValue.S_UpCatalogId,upCatalogId,condition,params,false);
        SQLCon.connectSQL(ICatalogValue.S_DelFlag,1L,condition,params,false);
        return catalogDAO.queryCatalogByCondition(condition.toString(),params,-1,-1);
    }

    /**
     * 新增目录
     * @param catalogName 目录名称
     * @param catalogId 目录id
     * @param addType 新增的类型 1是新增同级目录 2是新增下级目录
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCatalog(String catalogName,long leval,long upCatalogId,long catalogId,int addType)throws Exception{
        CatalogBean bean = new CatalogBean();
        if(addType == 1){//新增同级目录
            bean.setLeval(leval);
            bean.setUpCatalogId(upCatalogId);

        }else if(addType ==2){//下级目录
            bean.setLeval(leval+1);
            bean.setUpCatalogId(catalogId);
        }
        bean.setCatalogName(catalogName);
        bean.setDelFlag(1L);
        bean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        catalogDAO.save(bean);
    }

    /**
     * 删除目录
     * @param catalogId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delCatalog(long catalogId) throws Exception{
        List<ICatalogValue> catalogValueList = queryCatalogByCatalogId(catalogId);
        if(catalogValueList == null || catalogValueList.size()<= 0){
            throw new Exception("删除的目录不存在");
        }
        ICatalogValue catalogValue = catalogValueList.get(0);
        catalogValue.setDelFlag(0L);
        catalogDAO.save(catalogValue);
    }
}
