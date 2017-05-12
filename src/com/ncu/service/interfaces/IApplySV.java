package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IApplyValue;
import net.sf.json.JSONObject;

/**
 * Created by xiaoou on 2017/4/18.
 */
public interface IApplySV {
    /**
     * 保存认证信息
     * @param jsonObject 页面传进来的json串
     * @throws Exception
     */
    public void saveApplyInfoByJSONObject(JSONObject jsonObject,long userId)throws Exception;

    /**
     * 查询认证信息
     * @param applyId
     * @return
     * @throws Exception
     */
    public IApplyValue queryApplyById(long applyId)throws Exception;

}
