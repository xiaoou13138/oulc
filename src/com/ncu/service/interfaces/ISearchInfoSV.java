package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IWebInfoValue;
import net.sf.json.JSONArray;

import java.util.List;

public interface ISearchInfoSV {

	/**
	 * 根据参数查询信息
	 * 需要递归
	 */
	public JSONArray getHtmlByParams(JSONArray paramArray, int num, int begin, int end) throws Exception;
	public String createHtml(IWebInfoValue value);

	/**
	 * 根据网页信息的主键查询网页的信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IWebInfoValue queryWebInfoById(long id) throws Exception;
}
