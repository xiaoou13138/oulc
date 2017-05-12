package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IWebInfoValue;

public interface IWebInfoSV {
	public void save(IWebInfoValue value) throws Exception;

	/**
	 * 设置评级
	 * @param webId
	 * @param leval
	 * @throws Exception
	 */
	public void saveLeval(long webId,long leval)throws Exception;

	/**
	 * 查询网页信息
	 * @param webId
	 * @throws Exception
	 */
	public IWebInfoValue queryWebInfoByWebId(long webId)throws Exception;

}
