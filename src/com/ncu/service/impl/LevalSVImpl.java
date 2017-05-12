package com.ncu.service.impl;

import com.ncu.dao.interfaces.ILevalDAO;
import com.ncu.service.interfaces.ILevalSV;
import com.ncu.table.bean.LevalBean;
import com.ncu.table.ivalue.ILevalValue;
import com.ncu.util.TimeUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by xiaoou on 2017/5/10.
 */
@Service("LevalSVImpl")
public class LevalSVImpl implements ILevalSV {
    @Resource(name="LevalDAOImpl")
    private ILevalDAO levalDAO;
    /**
     * 保存评级
     * @param value
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(ILevalValue value)throws Exception{
        levalDAO.save(value);
    }

    /**
     * 保存评级
     * @param json
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveLevalByJson(JSONObject json)throws Exception{
        long leval = json.getLong("leval");
        long webId = json.getLong("webId");
        ILevalValue levalValue = new LevalBean();
        levalValue.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
        levalValue.setEntityId(webId);
        levalValue.setEntityType(1L);
        levalValue.setLeval(leval);
        save(levalValue);
    }
}
