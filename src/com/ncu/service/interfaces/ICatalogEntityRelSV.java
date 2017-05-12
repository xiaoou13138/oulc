package com.ncu.service.interfaces;

import com.ncu.table.ivalue.ICatalogEntityRelValue;
import com.ncu.table.ivalue.ICatalogValue;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
public interface ICatalogEntityRelSV {
    /**
     * 保存目录和实体的信息
     * @param value
     * @throws Exception
     */
    public void saveCatalogEntityRel(ICatalogEntityRelValue value)throws Exception;

    /**
     * 查询实体和目录的关系
     * @param entityId
     * @param entityType
     * @return
     * @throws Exception
     */
    public List<ICatalogEntityRelValue> queryCatalogEntityRelByEntityIdAndType(long entityId,long entityType)throws Exception;

    /**
     * 查询目录和实体的关系
     * @param catalogId
     * @param entityType 1是组织和目录的关系 2是资源和目录的关系
     * @return
     * @throws Exception
     */
    public List<ICatalogEntityRelValue> queryCatalogEntityRelByCatalogIdAndType(long catalogId,long entityType)throws Exception;


}
