package com.ncu.controler;

import com.ncu.crawler.BfsSpider;
import com.ncu.data.ViewData;
import com.ncu.util.APPUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/5/8.
 */
@Controller
@Scope("prototype")
public class CrawlerController extends NotCheckUserController {
    @Resource(name="BfsSpider")
    private BfsSpider bfsSpider;

    @RequestMapping(value="/crawler")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("crawler");
        mv.addObject("data",data);
        return mv;
    }

    @RequestMapping(value="/crawler_dealAction" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object dealAction(){
        String rtn = "Y";
        JSONObject rtnObject = this.getObject();
        try {
            JSONObject viewContent = this.getViewJSON();
            long userId = getLongParamFromSession("userId");
            int actionType = viewContent.getInt("actionType");
            if(actionType == 1){
                JSONArray urlArray = viewContent.getJSONArray("urlArray");
                bfsSpider.initCrawlerWithSeeds((String[])urlArray.toArray(new String[0]));
            }else if(actionType == 2){
                bfsSpider.startWork();
            }else if(actionType == 3){
                bfsSpider.stopCrawl();
            }else if(actionType == 4){
                //查询爬取过程信息 获取已经爬取的url吧
                long downFileCount = bfsSpider.getDownFileCount();
                long findFileCount = bfsSpider.getFindFileCount();
                rtnObject.put("downFileCount",downFileCount);
                rtnObject.put("findFileCount",findFileCount);
            }

        }catch (Exception e){
            rtn = "N";
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }
}
