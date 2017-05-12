package com.ncu.service.impl;

import javax.annotation.Resource;

import com.ncu.cache.WordCache;
import com.ncu.crawler.DownTool;
import com.ncu.crawler.JcsegUtil;
import com.ncu.crawler.dealInfo.DealData;
import com.ncu.dao.interfaces.*;
import com.ncu.service.interfaces.IWebInfoSV;
import com.ncu.service.interfaces.IWordLinkSV;
import com.ncu.table.bean.MessageBean;
import com.ncu.table.bean.ParamsDefine;
import com.ncu.table.bean.WebInfoBean;
import com.ncu.table.ivalue.IMessageValue;
import com.ncu.table.ivalue.IWebInfoValue;
import com.ncu.util.SQLCon;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ncu.service.interfaces.ISearchInfoSV;

import java.util.*;

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

	@Autowired
	private DownTool downTool;

	@Autowired
	private DealData dealData;




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

	/**
	 * 查询网页信息
	 * @param searchContent
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public HashMap queryWebInfo(String searchContent, int begin, int end)throws Exception{
		HashMap rtnMap = new HashMap();
		//分词
		List<IWebInfoValue> webList = queryWebInfoBySearchContent(searchContent,begin,end);
		long count = queryWebInfoCountBySearchContent(searchContent);
		rtnMap.put("count",count);
		if(webList != null && webList.size()>0){
			ArrayList rtnList = new ArrayList();
			for(int i = 0;i<webList.size();i++){
				IWebInfoValue webInfoValue = webList.get(i);
				HashMap map = new HashMap();
				if(webInfoValue.getTitle() != null){
					map.put("title",webInfoValue.getTitle());
				}
				map.put("url",webInfoValue.getUrl());
				map.put("id",webInfoValue.getId());
				rtnList.add(map);
			}
			rtnMap.put("webList",rtnList);
		}
		return  rtnMap;
	}

	/**
	 * 查询网页信息
	 * @param SearchContent
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<IWebInfoValue> queryWebInfoBySearchContent(String SearchContent, int begin, int end)throws Exception{
		Set<String> wordSet = JcsegUtil.devideWord(SearchContent);
		Iterator<String> iterator =  wordSet.iterator();
		String sql = "select c.id,c.url,c.title from WordBean a,WordLinkBean b,WebInfoBean c "
				+"where a.wordId = b.wordId and b.webInfoId = c.id  ";
		List<IWebInfoValue> rtnList = new ArrayList();
		ArrayList<ParamsDefine> paramsDefineArrayList = new ArrayList<>();
		if(iterator.hasNext()){
			sql+="and (";
		}
		if(iterator.hasNext()){
			int count = 0;

			while(iterator.hasNext()){
				if(count == 0){
					sql+="a.word LIKE :word"+count;
				}else{
					sql+=" or a.word LIKE :word"+count;
				}
				String word = iterator.next();
				if(StringUtils.isNotBlank(word)){
					ParamsDefine paramsBean = new ParamsDefine();
					paramsBean.setColName("word"+count);
					paramsBean.setIsList(false);
					paramsBean.setParamVal('%'+word+'%');
					paramsDefineArrayList.add(paramsBean);
				}
				count++;
			}
			sql +=")";
		}
		sql +=" GROUP BY c.id ORDER BY count(c.id) DESC";
		Object obj =  commonQueryDAO.commonQuery(sql,paramsDefineArrayList.toArray(new ParamsDefine[0]),begin,end);
		if(obj != null){
			ArrayList list = (ArrayList)obj;
			for(int i = 0;i<list.size();i++){
				Object object[] = (Object[]) list.get(i);
				IWebInfoValue webInfoValue = new WebInfoBean();
				webInfoValue.setId((long)object[0]);
				webInfoValue.setUrl((String)object[1]);
				if(object.length == 3 ){
					webInfoValue.setTitle((String)object[2]);
				}
				rtnList.add(webInfoValue);
			}
			return rtnList;
		}
		return null;
	}
	public long queryWebInfoCountBySearchContent(String SearchContent)throws Exception{
		Set<String> wordSet = JcsegUtil.devideWord(SearchContent);
		Iterator<String> iterator =  wordSet.iterator();
		String sql = "from WebInfoBean d where d.id in (select c.id from WordBean a,WordLinkBean b,WebInfoBean c "
				+"where a.wordId = b.wordId and b.webInfoId = c.id  ";
		List rtnList = null;
		ArrayList<ParamsDefine> paramsDefineArrayList = new ArrayList<>();
		if(iterator.hasNext()){
			sql+="and (";
		}
		if(iterator.hasNext()){
			int count = 0;

			while(iterator.hasNext()){
				if(count == 0){
					sql+="a.word LIKE :word"+count;
				}else{
					sql+=" or a.word LIKE :word"+count;
				}
				String word = iterator.next();
				if(StringUtils.isNotBlank(word)){
					ParamsDefine paramsBean = new ParamsDefine();
					paramsBean.setColName("word"+count);
					paramsBean.setIsList(false);
					paramsBean.setParamVal('%'+word+'%');
					paramsDefineArrayList.add(paramsBean);
				}
				count++;
			}
			sql +=")";
		}
		sql +=" GROUP BY c.id)";
		return commonQueryDAO.getCount(sql,paramsDefineArrayList.toArray(new ParamsDefine[0]));
	}

	/**
	 * 查询网页正文
	 * @param url
	 * @param searchContent
	 * @return
	 * @throws Exception
	 */
	public HashMap queryWebContent(String url,String searchContent)throws Exception{
		HashMap rtnMap = new HashMap();
		String content = downTool.getContent(url);
		content = content.substring(content.indexOf("<body>"),content.indexOf("</body>"));
		content = dealData.regexHtml(content);
		content = content.replace("\r\n","");
		content = content.replace("&mdash","");
		content = content.replace("\t","");
		content = content.replace(" ","");
		String words[] = dealData.devideWord(searchContent);
		int index = 0;
		for(int i = 0;i<words.length;i++){
			index = content.indexOf(words[i]);
			if(index != -1){
				break;
			}
		}
		int contentLength = 150;
		String rtnString = "";
		if(content.length()>contentLength){
			if(content.length()-index>contentLength){
				rtnString = content.substring(index,index+contentLength);
			}else{
				rtnString = content.substring(content.length()-contentLength,content.length());
			}
		}else{
			rtnString = content;
		}
		for(int i=0;i<words.length;i++){
			rtnString = rtnString.replace(words[i],"<span style='color: red;'>"+words[i]+"</span>");
		}
		rtnMap.put("html",rtnString);
		return rtnMap;
	}
}
