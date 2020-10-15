package com.dataservice.websocket.dpwebsocket.controller;

import com.dataservice.websocket.dpwebsocket.config.ServerConfig;
import com.dataservice.websocket.dpwebsocket.util.ServerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 获取主机和端口设置
     * @param host
     * @param port
     * @return
     */
    private String getHostPort(String host,String port){
        if(host==null||"".equals(host)){
            host=serverIP();
        }
        if(port==null||"".equals(port)){
            port=environmentPort2();
        }
        return host+":"+port;
    }

    @RequestMapping("/websocket")
    public String websocket(ModelMap map, /*@RequestParam("host")*/ String host,/* @RequestParam("port")*/ String port){
        String host_port=getHostPort(host,port);
        map.put("host_port",host_port);
        return "websocket";
    }

    @RequestMapping("/push")
    public String push(ModelMap map, /*@RequestParam("host")*/ String host, /*@RequestParam("port")*/ String port){
        String host_port=getHostPort(host,port);
        map.put("host_port",host_port);
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
