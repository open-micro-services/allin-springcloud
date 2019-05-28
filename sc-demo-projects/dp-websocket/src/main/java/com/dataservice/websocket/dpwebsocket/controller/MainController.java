package com.dataservice.websocket.dpwebsocket.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @RequestMapping("/")
    public ModelAndView root(){
        return new ModelAndView("index");
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping("/websocket")
    public ModelAndView websocket(){
        return new ModelAndView("websocket");
    }
}
