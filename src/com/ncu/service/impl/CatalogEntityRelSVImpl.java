package com.ncu.service.impl;

import com.ncu.dao.interfaces.ICatalogEntityRelDAO;
import com.ncu.service.interfaces.ICatalogEntityRelSV;
import com.ncu.table.ivalue.ICatalogEntityRelValue;
import com.ncu.util.SQLCon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaoou on 2017/4/18.
 */
@Service("CatalogEntityRelSVImpl")
public class CatalogEntityRelSVImpl implements ICatalogEntityRelSV{
    @Resource(name="CatalogEntityRelDAOImpl")
    private ICatalogEntityRelDAO catalogEntityRelDAO;

    /**
     * 保存目录和实体的信息
     * @param value
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCatalogEntityRel(ICatalogEntityRelValue value)throws Exception{
        catalogEntityRelDAO.save(value);
    }

    /**
     * 查询实体和目录的关系
     * @param entityId
     * @param entityType 1是组织和目录关系 2是目录和资源关系
     * @return
     * @throws Exception
     */
    public List<ICatalogEntityRelValue> queryCatalogEntityRelByEntityIdAndType(long entityId, long entityType)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(ICatalogEntityRelValue.S_EntityId,entityId,condition,params,false);
        SQLCon.connectSQL(ICatalogEntityRelValue.S_EntityType,entityType,condition,params,false);
        return catalogEntityRelDAO.queryCatalogEntityRelByCondition(condition.toString(),params,-1,-1);
    }

    /**
     * 查询目录和实体的关系
     * @param catalogId
     * @param entityType
     * @return
     * @throws Exception
     */
    public List<ICatalogEntityRelValue> queryCatalogEntityRelByCatalogIdAndType(long catalogId,long entityType)throws Exception{
        StringBuilder condition = new StringBuilder();
        HashMap params = new HashMap();
        SQLCon.connectSQL(ICatalogEntityRelValue.S_CatalogId,catalogId,condition,params,false);
        SQLCon.connectSQL(ICatalogEntityRelValue.S_EntityType,entityType,condition,params,false);
        return catalogEntityRelDAO.queryCatalogEntityRelByCondition(condition.toString(),params,-1,-1);
    }

}
