package com.zmn.xht.dataservice.zmndataservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MainController {

    @RequestMapping("/")
    public String root(){
       return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
