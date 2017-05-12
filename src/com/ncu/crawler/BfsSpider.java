package com.ncu.crawler;


import org.infinispan.commons.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Service("BfsSpider")

public class BfsSpider{
	@Autowired
	private DownTool downLoader ;

	private boolean startFlag = true;

	public synchronized void stopCrawl(){
		startFlag = false;
	};
	long beginTime;
	long downFileCount = 0;
	long findFileCount = 0;;
	public void startWork(){
		startFlag = true;
		beginTime = System.currentTimeMillis();//开始时间
		ArrayList threadList = new ArrayList();
		for(int i = 0;i<5;i++){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						LinkFilter filter = new LinkFilter() {
							public boolean accept(String url) {
								return true;
							}
						};
						// 循环条件：待抓取的链接不空且抓取的网页不多于 1000
						while (!SpiderQueue.unVisitedUrlsEmpty() && SpiderQueue.getVisitedUrlNum() <= 1000 && startFlag) {
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
							long endTime = System.currentTimeMillis();
							if(endTime - beginTime < 1000){
								downFileCount++;
								findFileCount = findFileCount+links.size();
							}else{
								downFileCount = 1;
								findFileCount = links.size();
							}
						}
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}

	}
	/** 
	  * 使用种子初始化URL队列 
	  */
	public void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++){
			SpiderQueue.addUnvisitedUrl(seeds[i]);
		}
	}

	/**
	 * 查询爬取的信息
	 * @return
	 */
	public HashMap queryCrawlInfo(){
		HashMap rtnMap = new HashMap();
		return rtnMap;
	}
	public long getDownFileCount(){
		return this.downFileCount;
	}
	public long getFindFileCount(){
		return findFileCount;
	}
}
