package com.ncu.service.impl;

import com.ncu.dao.interfaces.ICommonDAO;
import com.ncu.dao.interfaces.IResourceDAO;
import com.ncu.service.interfaces.ICatalogEntityRelSV;
import com.ncu.service.interfaces.ICatalogSV;
import com.ncu.service.interfaces.IOrgDefSV;
import com.ncu.service.interfaces.IResourceSV;
import com.ncu.table.bean.CatalogEntityRelBean;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.table.bean.ResourceBean;
import com.ncu.table.ivalue.ICatalogEntityRelValue;
import com.ncu.table.ivalue.ICatalogValue;
import com.ncu.table.ivalue.IOrgDefValue;
import com.ncu.table.ivalue.IResourceValue;
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
 * Created by xiaoou on 2017/4/20.
 */
@Service("ResourceSVImpl")
public class ResourceSVImpl implements IResourceSV {
    @Resource(name="ResourceDAOImpl")
    private IResourceDAO resourceDAO;

    @Resource(name="CommonDAOImpl")
    private ICommonDAO commonDAO;

    @Resource(name="CatalogEntityRelSVImpl")
    private ICatalogEntityRelSV catalogEntityRelSV;

    @Resource(name="CatalogSVImpl")
    private ICatalogSV catalogSV;

    @Resource(name="OrgDefSVImpl")
    private IOrgDefSV orgDefSV;

    /**
     * 查询资源信息
     * @param resourceId 资源主键
     * @return
     * @throws Exception
     */
    public IResourceValue queryResourceById(long resourceId)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(IResourceValue.S_ResourceId,resourceId,condition,params,false);
        SQLCon.connectSQL(IResourceValue.S_DelFlag,1L,condition,params,false);
        List<IResourceValue> list = resourceDAO.queryResourceByCondition(condition.toString(),params,-1,-1);
        if(list != null &&list.size() > 0){
            return list.get(0);
        }
        return null;
    }



    /**
     * 保存资源信息
     * @param json
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveResource(JSONObject json)throws Exception{
        IResourceValue value = new ResourceBean();
        value.setName(json.getString("name"));
        value.setDelFlag(1L);
        value.setCreateData(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        if(json.containsKey("num")){
            value.setNum(json.getLong("num"));
        }
        if(json.containsKey("unit")){
            value.setUnit(json.getString("unit"));
        }
        if(json.containsKey("desc")){
            value.setDescription(json.getString("desc"));
        }
        resourceDAO.save(value);
        //保存目录和资源的关系
        ICatalogEntityRelValue catalogEntityRelValue = new CatalogEntityRelBean();
        long catalogId = json.getLong("catalogId");
        catalogEntityRelValue.setCatalogId(catalogId);
        catalogEntityRelValue.setEntityType(2L);
        catalogEntityRelValue.setEntityId(value.getResourceId());
        catalogEntityRelValue.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        catalogEntityRelSV.saveCatalogEntityRel(catalogEntityRelValue);
    }

    /**
     * 查询资源信息
     * @param catalogId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryResourceByCatalogIdForController(long catalogId,int begin,int end)throws Exception{
        HashMap rtnMap = new HashMap();
        List<IResourceValue> list = queryResourceByCatalog(catalogId,begin,end);
        long count = queryResourceCountByCatalog(catalogId);
        rtnMap.put("count",count);
        if(list != null && list.size() >0){
            ArrayList rtnList = new ArrayList();
            int length = list.size();
            for(int i = 0;i<length;i++){
                HashMap map = new HashMap();
                IResourceValue resourceValue = list.get(i);
                map.put("resourceId",resourceValue.getResourceId());
                if(resourceValue.getName() != null){
                    map.put("name",resourceValue.getName());
                }
                if(resourceValue.getNum() != null){
                    map.put("num",resourceValue.getNum());
                }
                if(resourceValue.getUnit() != null){
                    map.put("unit",resourceValue.getUnit());
                }
                if(resourceValue.getDescription() != null){
                    map.put("Dsc",resourceValue.getDescription());
                }
                rtnList.add(map);
            }
            rtnMap.put("resourceList",rtnList);
        }
        return rtnMap;
    }
    public List<IResourceValue> queryResourceByCatalog(long catalogId,int begin,int end)throws Exception{
        String sql = "select a from ResourceBean a,CatalogEntityRelBean b where a.resourceId = b.entityId and b.entityType = 2 and b.catalogId = :catalogId and a.delFlag=1";
        ArrayList<ParamsDefine> paramsDefines = new ArrayList<>();
        ParamsDefine paramsDefine = new ParamsDefine();
        paramsDefine.setIsList(false);
        paramsDefine.setParamVal(catalogId);
        paramsDefine.setColName("catalogId");
        paramsDefines.add(paramsDefine);
        return commonDAO.commonQuery(sql,paramsDefines.toArray(new ParamsDefine[0]),begin,end);
    }
    public long queryResourceCountByCatalog(long catalogId)throws Exception{
        String sql = "from ResourceBean a,CatalogEntityRelBean b where a.resourceId = b.entityId and b.entityType = 2 and b.catalogId = :catalogId and a.delFlag=1";
        ArrayList<ParamsDefine> paramsDefines = new ArrayList<>();
        ParamsDefine paramsDefine = new ParamsDefine();
        paramsDefine.setIsList(false);
        paramsDefine.setParamVal(catalogId);
        paramsDefine.setColName("catalogId");
        paramsDefines.add(paramsDefine);
        return commonDAO.getCount(sql,paramsDefines.toArray(new ParamsDefine[0]));
    }

    /**
     * 删除资源
     * @param resourceArray
     * @param userId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteResource(JSONArray resourceArray, long userId)throws Exception{
        if(resourceArray != null && resourceArray.size()>0){
            int length = resourceArray.size();
            for(int i= 0;i<length;i++){
                long resourceId = resourceArray.getLong(i);
                IResourceValue resourceValue = queryResourceById(resourceId);
                if(resourceValue == null ){
                    throw new Exception("资源不存在");
                }
                resourceValue.setDelFlag(0L);
                resourceDAO.save(resourceValue);
            }
        }
    }
    /**
     * 查询资源
     * @param resourceId
     * @return
     * @throws Exception
     */
    public HashMap queryResourceByIdForController(long resourceId)throws Exception{
        IResourceValue resourceValue = queryResourceById(resourceId);
        if(resourceValue == null){
            throw new Exception("资源不存在");
        }
        HashMap rtnMap = new HashMap();
        rtnMap.put("name",resourceValue.getName());

        if(resourceValue.getNum() != null){
            rtnMap.put("num",resourceValue.getNum());
        }
        if(resourceValue.getUnit() != null){
            rtnMap.put("unit",resourceValue.getUnit());
        }
        if(resourceValue.getUnit() != null){
            rtnMap.put("desc",resourceValue.getDescription());
        }
        return rtnMap;
    }

    /**
     * 更新资源信息
     * @param json
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateResource(JSONObject json)throws Exception{
        long resourceId = json.getLong("resourceId");
        IResourceValue resourceValue = queryResourceById(resourceId);
        if(resourceValue ==  null){
            throw new Exception("资源不存在");
        }
        resourceValue.setName(json.getString("name"));
        if(json.containsKey("num")){
            resourceValue.setNum(json.getLong("num"));
        }
        if(json.containsKey("unit")){
            resourceValue.setUnit(json.getString("unit"));
        }
        if(json.containsKey("desc")){
            resourceValue.setDescription(json.getString("desc"));
        }
        resourceDAO.save(resourceValue);
    }

    /**
     * 查询资源
     * @param entityId
     * @return
     * @throws Exception
     */
    public HashMap queryResourceByEntityIdAndType(long entityId)throws Exception{
        HashMap rtnMap = new HashMap();
        //根据组织主键查询关联的一级目录
        List<ICatalogEntityRelValue> catalogEntityRelValueList = catalogEntityRelSV.queryCatalogEntityRelByEntityIdAndType(entityId,1L);
        if(catalogEntityRelValueList == null || catalogEntityRelValueList.size()<=0){
            throw new Exception("该组织没有一级目录");
        }
        ICatalogEntityRelValue catalogEntityRelValue = catalogEntityRelValueList.get(0);
        long catalogId = catalogEntityRelValue.getCatalogId();
        getCatalogAndResource(rtnMap,catalogId);
        //查询组织的信息
        IOrgDefValue defValue = orgDefSV.queryOrgDefByOrgId(entityId);
        if(defValue ==null){
            throw new Exception("组织不存在");
        }
        rtnMap.put("orgName",defValue.getOrgName());
        rtnMap.put("orgDesc",defValue.getOrgDesc());
        return rtnMap;
    }
    public void getCatalogAndResource(HashMap upMap,long catalogId)throws Exception{
        //查询资源目录关系
        List<ICatalogEntityRelValue> catalogEntityRelValueList = catalogEntityRelSV.queryCatalogEntityRelByCatalogIdAndType(catalogId,2L);
        if(catalogEntityRelValueList != null && catalogEntityRelValueList.size()>0){
            ArrayList rtnList = new ArrayList();
            int length = catalogEntityRelValueList.size();
            for(int i = 0;i<length ;i++){
                long resourceId = catalogEntityRelValueList.get(i).getEntityId();
                //查询资源
                IResourceValue resourceValue = queryResourceById(resourceId);
                if(resourceValue == null){
                    throw new Exception("资源不存在");
                }
                HashMap map = new HashMap();
                map.put("name",resourceValue.getName());
                map.put("id",resourceValue.getResourceId());
                if(resourceValue.getDescription() != null){
                    map.put("Dsc",resourceValue.getDescription());
                }
                if(resourceValue.getUnit() != null){
                    map.put("unit",resourceValue.getUnit());
                }
                if(resourceValue.getNum() != null){
                    map.put("num",resourceValue.getNum());
                }
                rtnList.add(map);
            }
            upMap.put("resourceList",rtnList);
        }
        //查询是否有下级目录

        List<ICatalogValue> catalogValueList = catalogSV.queryCatalogByUpCatalogId(catalogId);
        if(catalogValueList != null && catalogValueList.size()>0){
            ArrayList catalogList = new ArrayList();
            int length = catalogValueList.size();
            for(int i = 0;i<length;i++){
                HashMap downMap = new HashMap();
                ICatalogValue catalogValue = catalogValueList.get(i);
                downMap.put("catalogName",catalogValue.getCatalogName());
                long downCatalogId = catalogValue.getCatalogId();
                downMap.put("catalogId",downCatalogId);

                if(catalogValue.getCatalogDesc()!= null){
                    downMap.put("desc",catalogValue.getCatalogDesc());
                }

                getCatalogAndResource(downMap,downCatalogId);
                catalogList.add(downMap);
            }
            upMap.put("catalogList",catalogList);
        }
    }

}
