package com.taotao.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Qin PengCheng
 * @date 2018/5/11
 */

@Controller
@RequestMapping("/page")
public class PageController {

    public PageController() {
        System.out.println("初始化.................................");
    }

    @RequestMapping(value="{pageName}", method= RequestMethod.GET)
    public String page(@PathVariable("pageName") String pageNum){
            return pageNum;
    }

  /*  @RequestMapping("/show")
    public String toIndex(){
        System.out.println(123+"................................................................................................................ ");
        return "index";
    }*/
}
