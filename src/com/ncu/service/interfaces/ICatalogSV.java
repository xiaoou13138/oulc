package com.ncu.service.interfaces;

import com.ncu.table.ivalue.ICatalogValue;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
public interface ICatalogSV {
    /**
     * 保存目录信息
     * @param value
     * @throws Exception
     */
    public void saveCatalog(ICatalogValue value)throws Exception;

    /**
     * 查找目录信息
     * @param userId 用户主键
     * @return
     * @throws Exception
     */
    public JSONObject getTreeInfo(long userId)throws Exception;

    /**
     * 创建目录容器
     * @param list
     * @param json
     * @throws Exception
     */
    public void putData(List<ICatalogValue> list,JSONObject json)throws Exception;
    /**
     * 查询目录的信息
     * @param catalogId 目录主键
     * @return
     * @throws Exception
     */
    public List<ICatalogValue> queryCatalogByCatalogId(long catalogId)throws Exception;

    /**
     * 查询目录信息
     * @param upCatalogId 上级目录主键
     * @return
     * @throws Exception
     */
    public List<ICatalogValue> queryCatalogByUpCatalogId(long upCatalogId)throws Exception;

    /**
     * 新增目录
     * @param catalogName 目录名称
     * @param catalogId 目录id
     * @param addType 新增的类型 1是新增同级目录 2是新增下级目录
     * @throws Exception
     */
    public void addCatalog(String catalogName,long leval,long upCatalogId,long catalogId,int addType)throws Exception;

    /**
     * 删除目录
     * @param catalogId
     * @throws Exception
     */
    public void delCatalog(long catalogId) throws Exception;
}
