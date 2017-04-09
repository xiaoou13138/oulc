package com.ncu.crawler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("BfsSpider")
public class BfsSpider {
	@Autowired
	private DownTool downLoader ;

	/** 
	  * 使用种子初始化URL队列 
	  */
	 private void initCrawlerWithSeeds(String[] seeds) {
	  for (int i = 0; i < seeds.length; i++)  
	   SpiderQueue.addUnvisitedUrl(seeds[i]);  
	 }  
	 // 定义过滤器，提取以 <a target=_blank href="http://www.xxxx.com/" style="color: rgb(0, 102, 153); text-decoration: none;">http://www.xxxx.com</a>开头的链接  
	 public void crawling(String[] seeds) throws Exception {  
	  LinkFilter filter = new LinkFilter() {  
	   public boolean accept(String url) {  
	    /*if (url.startsWith("http://www.bilibili.com/"))  
	     return true;  
	    else  
	     return false;  */
		   return true;
	   }  
	  };  
	  // 初始化 URL 队列  
	  initCrawlerWithSeeds(seeds);  
	  // 循环条件：待抓取的链接不空且抓取的网页不多于 1000  
	  while (!SpiderQueue.unVisitedUrlsEmpty()  
	    && SpiderQueue.getVisitedUrlNum() <= 1000) {  
	   // 队头 URL 出队列  
	   String visitUrl = (String) SpiderQueue.unVisitedUrlDeQueue();  
	   if (visitUrl == null)  
	    continue;  
	   // 下载网页
	   downLoader.downloadFile(visitUrl);  
	   // 该 URL 放入已访问的 URL 中  
	   SpiderQueue.addVisitedUrl(visitUrl);  
	   // 提取出下载网页中的 URL  
	   Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);  
	   // 新的未访问的 URL 入队  
	   for (String link : links) {  
	    SpiderQueue.addUnvisitedUrl(link);  
	   }  
	  }  
	 }  
	 // main 方法入口  
	  
}
