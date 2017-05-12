package com.ncu.service.interfaces;

import com.ncu.table.ivalue.ILevalValue;
import net.sf.json.JSONObject;

/**
 * Created by xiaoou on 2017/5/10.
 */
public interface ILevalSV {

    /**
     * 保存评级
     * @param value
     * @throws Exception
     */
    public void save(ILevalValue value)throws Exception;

    /**
     * 保存评级
     * @param json
     * @throws Exception
     */
    public void saveLevalByJson(JSONObject json)throws Exception;

}
