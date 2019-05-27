package com.boonya.springboot.websocket.sbwebsocket.controller;

import com.boonya.springboot.websocket.sbwebsocket.server.WebSocketServer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    //页面请求
    @RequestMapping(value = "/socket/{clientId}",method = RequestMethod.POST)
    public ModelAndView socket(@PathVariable("clientId") String clientId) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("clientId", clientId);
        return mav;
    }
    //推送数据接口
    @RequestMapping("/socket/push/{clientId}")
    @ResponseBody
    public Map<String,Object> pushMessageToPage(@PathVariable("clientId") String clientId, String message) {
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            WebSocketServer.broadcast(message,clientId);
            map.put("status",1);
            map.put("msg","消息发送成功!");
            map.put("data",message);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status",0);
            map.put("msg","消息发送失败!");
            map.put("data",message);
            return map;
        }
        return map;
    }

}
