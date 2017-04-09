package com.ncu.util;

import java.util.Map;


import net.sf.json.JSONObject;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ncu.data.ViewData;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpSession;

public class APPUtil {
	public static Object returnObject(ViewData data,Map map){
		if(data.containKey("callback")){
			String callback = data.getString("callback");
			return new JSONPObject(callback,map);
			}else{
				return map;
		}
	}

	/**
	 * 安全获得session里面的参数
	 * @param session
	 * @param paramName
	 * @return
	 */
	public static String getSafeParamsFromSession(HttpSession session,String paramName){
		Object paramsVal = session.getAttribute(paramName);
		if(paramsVal != null){
			return paramsVal.toString();
		}
		return "";
	}

	public static String getSafeStringFromJSONObject(JSONObject object,String key){
		String rtnValue = "";
		if(object != null && StringUtils.isNotBlank(key)){
			if(object.containsKey(key)){
				rtnValue = object.getString(key);
			}
		}
		return rtnValue;
	}
}