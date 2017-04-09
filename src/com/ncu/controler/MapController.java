package com.ncu.controler;

import com.ncu.data.ViewData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiaoou on 2017/3/29.
 */
@Controller
@Scope("prototype")
public class MapController extends  BaseController {
    @RequestMapping(value="/map")
    public ModelAndView toLogin()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getReturnViewData();
        mv.setViewName("map");
        mv.addObject("data",data);
        return mv;
    }
}
