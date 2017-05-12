package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IWebInfoValue;
import net.sf.json.JSONArray;

import java.util.HashMap;
import java.util.List;

public interface ISearchInfoSV {

	/**
	 * 根据网页信息的主键查询网页的信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IWebInfoValue queryWebInfoById(long id) throws Exception;

	/**
	 * 查询网页信息
	 * @param SearchContent
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public HashMap queryWebInfo(String SearchContent, int begin, int end)throws Exception;

	/**
	 * 查询网页信息
	 * @param SearchContent
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<IWebInfoValue> queryWebInfoBySearchContent(String SearchContent, int begin, int end)throws Exception;
	public long queryWebInfoCountBySearchContent(String SearchContent)throws Exception;

	/**
	 * 查询网页正文
	 * @param url
	 * @param searchContent
	 * @return
	 * @throws Exception
	 */
	public HashMap queryWebContent(String url,String searchContent)throws Exception;
}
