package com.ncu.cache;

import com.ncu.service.interfaces.IStaticDataSV;
import com.ncu.table.ivalue.IStaticDataValue;
import org.apache.commons.lang.StringUtils;
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
    /**
     * 根据code从静态表里面获取codeValue
     * @param code
     * @return
     */
    public String getCodeValueByCode(String code){
        if(StringUtils.isNotBlank(code)){
            List<IStaticDataValue> list = getStaticDataByCode(code);
            if(list !=null &&list.size() == 1){
                return list.get(0).getCodeValue();
            }
        }
        return null;
    }

    public String getCodeNameByCodeTypeAndValue(String codeType,String codeValue){
        List<IStaticDataValue> list = getStaticDataByCode(codeType);
        if(list != null && list.size()>0){
            int length = list.size();
            for(int i= 0;i<length;i++){
                IStaticDataValue staticDataValue = list.get(i);
                if(codeValue.equals(staticDataValue.getCodeValue())){
                    return staticDataValue.getCodeName();
                }
            }
        }
        return null;
    }
}
