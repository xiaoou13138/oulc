package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IOrgDefValue;

import java.util.List;

/**
 * Created by xiaoou on 2017/4/18.
 */
public interface IOrgDefSV {
    /**
     * 查询组织的信息
     * @param userId 用户主键
     * @return
     */
    public IOrgDefValue queryOrgDefByUserId(long userId)throws Exception;

    /**
     * 保存组织的信息
     * @throws Exception
     */
    public long saveOrgInfo(long applyId)throws Exception;

    /**
     * 查询组织的信息
     * @param orgId
     * @return
     * @throws Exception
     */
    public IOrgDefValue queryOrgDefByOrgId(long orgId)throws Exception;
}
