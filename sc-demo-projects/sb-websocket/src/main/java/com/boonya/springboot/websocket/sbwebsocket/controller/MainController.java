package com.boonya.springboot.websocket.sbwebsocket.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @RequestMapping("/")
    public ModelAndView root() {
        ModelAndView mv=new ModelAndView("redirect:/index");
        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv=new ModelAndView("index");
        return mv;
    }

    @RequestMapping("/user/index")
    public ModelAndView userIndex() {
        ModelAndView mv=new ModelAndView("user/index");
        return mv;
    }

    @RequestMapping("/websocket/socket")
    public ModelAndView websocket() {
        ModelAndView mv=new ModelAndView("websocket/socket");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv=new ModelAndView("login");
        return mv;
    }

    @RequestMapping("/login-error")
    public ModelAndView loginError(Model model) {
        model.addAttribute("loginError", true);
        ModelAndView mv=new ModelAndView("login");
        return mv;
    }
}
