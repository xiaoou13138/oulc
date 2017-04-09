package com.ncu.service.interfaces;

import com.ncu.table.ivalue.IWordLinkValue;
import java.util.List;
public interface IWordLinkSV {
	/**
	 * 保存信息
	 * @param value
	 * @throws Exception
	 */
	public void save(IWordLinkValue value) throws Exception;

	/**
	 * 根据词条的主键去查询关联表
	 * @param wordIds
	 * @return
	 * @throws Exception
	 */
	public List<IWordLinkValue> queryLinkInfoByWordIds(String wordIds[]) throws Exception;
}
