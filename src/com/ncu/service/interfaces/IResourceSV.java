package com.ncu.service.interfaces;
import com.ncu.table.ivalue.IResourceValue;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.infinispan.commons.hash.Hash;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/20.
 */
public interface IResourceSV {
    /**
     * 查询资源信息
     * @param resourceId 资源主键
     * @return
     * @throws Exception
     */
    public IResourceValue queryResourceById(long resourceId)throws Exception;

    /**
     * 查询资源信息
     * @param catalogId
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public HashMap queryResourceByCatalogIdForController(long catalogId,int begin,int end)throws Exception;
    public List<IResourceValue> queryResourceByCatalog(long catalogId,int begin,int end)throws Exception;
    public long queryResourceCountByCatalog(long catalogId)throws Exception;

    /**
     * 保存资源信息
     * @param json
     * @throws Exception
     */
    public void saveResource(JSONObject json)throws Exception;

    /**
     * 删除资源
     * @param resourceArray
     * @param userId
     * @throws Exception
     */
    public void deleteResource(JSONArray resourceArray,long userId)throws Exception;

    /**
     * 查询资源
     * @param resourceId
     * @return
     * @throws Exception
     */
    public HashMap queryResourceByIdForController(long resourceId)throws Exception;

    /**
     * 更新资源信息
     * @param json
     * @throws Exception
     */
    public void updateResource(JSONObject json)throws Exception;

    /**
     * 查询资源
     * @param entityId
     * @return
     * @throws Exception
     */
    public HashMap queryResourceByEntityIdAndType(long entityId)throws Exception;

}
