package com.boonya.springboot.mybatis.sbmybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@RestController
@Controller
@RequestMapping
public class MainController {

    /*************@RestController 注解需要的写ModelAndView**********/
   /* @RequestMapping("/")
    public ModelAndView root(){
        return new ModelAndView("/index");
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }*/

    /*************@Controller 注解只需要写文件名字符串返回即可**********/

    @RequestMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
