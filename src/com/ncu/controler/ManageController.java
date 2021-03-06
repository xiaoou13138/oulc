package com.ncu.controler;

import com.ncu.data.ViewData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiaoou on 2017/3/26.
 */
@Controller
@Scope("prototype")
public class ManageController extends  BaseController {
    /**
     * 用户请求网页
     * @return
     */
    @RequestMapping(value="/manage")
    public ModelAndView toLogin()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = getReturnViewData();
        mv.setViewName("manage");
        mv.addObject("data",data);
        return mv;
    }
}
