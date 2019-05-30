package com.dataservice.websocket.dpwebsocket.controller;

import com.dataservice.websocket.dpwebsocket.config.ServerConfig;
import com.dataservice.websocket.dpwebsocket.util.ServerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 视图配置及服务器参数控制器
 */
@Controller
public class MainController {

    @Autowired
    Environment environment;

    @Autowired
    ServerConfig serverConfig;

    @RequestMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/websocket")
    public String websocket(ModelMap map){
        map.put("host_port",serverIP()+":"+environmentPort2());
        return "websocket";
    }

    @RequestMapping("/push")
    public String push(ModelMap map){
        map.put("host_port",serverIP()+":"+environmentPort2());
        return "push";
    }

    @RequestMapping("/server/ip")
    @ResponseBody
    public String serverIP(){
        return ServerUtil.getServerIp();
    }

    @RequestMapping("/server/url")
    @ResponseBody
    public String serverUrl(){
        return serverConfig.getBaseUrl();
    }

    @RequestMapping("/server/port")
    @ResponseBody
    public String environmentPort(){
        return environment.getProperty("local.server.port");
    }

    @RequestMapping("/server/port2")
    @ResponseBody
    public String environmentPort2(){
        return environment.getProperty("server.port");
    }

}
