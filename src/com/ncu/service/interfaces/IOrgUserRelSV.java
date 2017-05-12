package com.ncu.service.interfaces;
import com.ncu.table.ivalue.IOrgUserRelValue;

import java.util.List;
/**
 * Created by xiaoou on 2017/4/18.
 */
public interface IOrgUserRelSV {
    /**
     * 查询组织和用户的关系
     * @param userId 用户的主键
     * @return
     * @throws Exception
     */
    public IOrgUserRelValue queryOrgUserRelByUserId(long userId)throws Exception;

    /**
     * 保存用户和组织的关系
     * @param orgId
     * @param userId
     * @throws Exception
     */
    public void saveOrgUserRel(long orgId,long userId)throws Exception;

}
