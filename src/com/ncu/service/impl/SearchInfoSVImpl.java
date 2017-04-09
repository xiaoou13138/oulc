package com.ncu.service.impl;

import javax.annotation.Resource;

import com.ncu.cache.WordCache;
import com.ncu.dao.interfaces.*;
import com.ncu.service.interfaces.IWebInfoSV;
import com.ncu.service.interfaces.IWordLinkSV;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.table.ivalue.IWebInfoValue;
import com.ncu.util.SQLCon;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ncu.service.interfaces.ISearchInfoSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("SearchInfoSVImpl")
public class SearchInfoSVImpl implements ISearchInfoSV {
	@Resource(name="WebInfoSVImpl")
	private IWebInfoSV webInfoSV;//查询网页信息的sv
	@Resource(name="WordLinkSVImpl")
	private IWordLinkSV wordLinkSV;//查询词条和网页信息的关系
	@Autowired
	private WordCache cache;
	@Resource(name="CommonDAOImpl")
    private ICommonDAO commonQueryDAO;
	@Autowired
	@Qualifier("WebInfoDAOImpl")
	private IWebInfoDAO webInfoDAO;


	/**
	 * 根据网页传进来的参数查询网页的主键
	 * @param paramArray
	 * @param num
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONArray getHtmlByParams(JSONArray paramArray, int num, int begin, int end) throws Exception {
		//先获取缓存里面的词条
		JSONArray htmlArray = new JSONArray();
		int length = paramArray.size();
		if(num>length){
		    throw new Exception("传入的Num参数不正确");
        }
        List wordIdsList = new ArrayList();
		for(int i =0;i<num;i++){
			String param = paramArray.getString(i);
			int wordId = (int)cache.queryWordIdByWrod(param);
            wordIdsList.add(wordId);
			//获取词条和网页的关联 但是怎么排名
		}
		String sql = "select a.webInfoId as count from WordLinkBean a where a.wordId in(:paramsList) group by a.webInfoId order by a.webInfoId DESC" ;
        ParamsDefine paramsBean = new ParamsDefine();
        paramsBean.setColName("paramsList");
        paramsBean.setIsList(true);
        paramsBean.setParamListVal(wordIdsList);
		List rtnList = commonQueryDAO.commonQuery(sql,new ParamsDefine[]{paramsBean});
		//查询数据库里面的信息
		if(rtnList != null && rtnList.size()>0){
			length = rtnList.size();
			for(int i =0;i<length;i++){
				IWebInfoValue webInfoValue = queryWebInfoById(Long.parseLong(rtnList.get(0).toString()));
				String html = createHtml(webInfoValue);
				if(html != null){
					htmlArray.add(html);
				}
			}
		}
		return htmlArray;
	}

	/**
	 * 根据网页的信息生成Html
	 * @param value
	 * @return
	 */
	public String createHtml(IWebInfoValue value){
		String title = value.getTitle();
		String headContent = value.getHeadContent();
		String url = value.getUrl();
		StringBuilder showHtml = new StringBuilder();
		showHtml.append("<div value=\"")
				.append(value.getId())
				.append("\"")
				.append(">")
				.append("<a href=\"")
				.append(url)
				.append("\">")
				.append(title)
				.append("</a>")
				.append("<div>")
				.append(headContent)
				.append("</div>")
				.append("<a onclick=\"openCommentWindow($(this))\" value=\"")
				.append(value.getId())
				.append("\"")
				.append(">评价</a>");
		return showHtml.toString();
	}

	@Override
	public IWebInfoValue queryWebInfoById(long id) throws Exception {
		if(id>0){
			StringBuilder condition = new StringBuilder();
			HashMap params = new HashMap();
			SQLCon.connectSQL(IWebInfoValue.S_Id,id,condition,params,false);
			List<IWebInfoValue> list =  webInfoDAO.queryWebInfoByCondition(condition.toString(),params,-1,-1);
			if(list != null && list.size() ==1){
				return list.get(0);
			}
		}
		return null;
	}
}
