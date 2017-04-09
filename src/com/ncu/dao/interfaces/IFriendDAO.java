package com.ncu.dao.interfaces;
import com.ncu.table.ivalue.IFriendValue;

import java.util.HashMap;
import java.util.List;
/**
 * Created by xiaoou on 2017/3/27.
 */
public interface IFriendDAO {
    public void save(IFriendValue value) throws Exception;
    public List<IFriendValue> queryInfoByCodition(String condition, HashMap params,int begin,int end) throws Exception;
}
