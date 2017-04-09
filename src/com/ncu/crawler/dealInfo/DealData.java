package com.ncu.crawler.dealInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.lionsoul.jcseg.core.JcsegTaskConfig;
import org.springframework.beans.factory.annotation.Autowired;

import com.ncu.cache.WordCache;
import com.ncu.crawler.JcsegUtil;
import com.ncu.service.impl.WebInfoSVImpl;
import com.ncu.service.impl.WordLinkSVImpl;
import com.ncu.service.impl.WordSVImpl;
import com.ncu.table.bean.WebInfoBean;
import com.ncu.table.bean.WordBean;
import com.ncu.table.bean.WordLinkBean;
import com.ncu.util.TimeUtil;
import org.springframework.stereotype.Service;

/**
 * 把网页信息解析之后放到数据库中
 * @author oulc
 *
 */
@Service("DealData")
public class DealData {
	@Autowired
	private WordCache cache;
	@Autowired
	private WordLinkSVImpl wordLinkSV;
	@Autowired
	private WebInfoSVImpl webInfosv;
	public void dealData(String content,String url,String title) throws Exception{
		//先去掉html标签
		Set<String> set = devideWebInfo(content);
		Iterator <String> iterator = set.iterator();
		WebInfoBean webInfoBean = new WebInfoBean();
		webInfoBean.setCreateDate(TimeUtil.getCurrentTimeyyyyMMddhhmmss());
		webInfoBean.setDelFlag(1L);
		webInfoBean.setOperId(1L);
		webInfoBean.setUrl(url);
		webInfosv.save(webInfoBean);
		long webInfoId =webInfoBean.getId();
		while(iterator.hasNext()){
			String str = iterator.next();
			str.replaceAll("[\\pP\\pS\\pZ]", "");
			Date date = TimeUtil.getCurrentTimeyyyyMMddhhmmss();
			long wordId = cache.queryWordIdByWrod(str);
			if(wordId ==0 ){

				long id = 0;
				WordBean wordBean = new WordBean();
				wordBean.setCreateDate(date);
				wordBean.setDelFlag(1);
				wordBean.setWord(str);
				cache.save(wordBean);
				id = wordBean.getWordId();

				WordLinkBean wordLinkBean = new WordLinkBean();
				wordLinkBean.setCreateDate(date);
				wordLinkBean.setDelFlag(1);
				wordLinkBean.setWordId((int)id);
				wordLinkBean.setWebInfoId((int)webInfoId);
				wordLinkSV.save(wordLinkBean);
				
			}
		}
		
		//存取到数据库当中 如果词条已经存在就不存word表 如果不存在就存 WebInfo是存网站的Url信息  word_link表是关联表
		
	}
	public Set<String> devideWebInfo(String content){
		Set<String> set = new HashSet<String>();
		JcsegUtil util = new JcsegUtil();
		String result = util.segText(regexHtml(content), JcsegTaskConfig.COMPLEX_MODE);
		String resultS[] = result.split(",");
		for(String str:resultS){
			set.add(str);
		}
		return set;
	}
	/**
	 * 过滤掉所有的<>内容
	 * @param content
	 * @return
	 */
	public String regexHtml(String content){
		String regex = "<([^>]*)>";
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
			matcher.appendReplacement(sb, "");
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
