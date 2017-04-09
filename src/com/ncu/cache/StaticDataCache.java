package com.ncu.cache;

import com.ncu.service.interfaces.IStaticDataSV;
import com.ncu.table.ivalue.IStaticDataValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiaoou on 2017/3/27.
 */
@Service("StaticDataCache")
public class StaticDataCache {
    @Autowired
    @Qualifier("StaticDataSVImpl")
    private IStaticDataSV sv;
    @Cacheable(value="staticDataCache", key="#code")
    public List<IStaticDataValue> getStaticDataByCode(String code){
        try{
            System.out.println("开始读取缓存");
            return sv.queryStaticDataByCode(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
