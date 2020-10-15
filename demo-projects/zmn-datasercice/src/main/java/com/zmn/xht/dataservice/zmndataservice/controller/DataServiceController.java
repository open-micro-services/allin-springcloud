package com.zmn.xht.dataservice.zmndataservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@RestController
@RequestMapping("/dataService")
public class DataServiceController {

    /**
     * 获取单位人员树(是否根据权限过滤)
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getTree.do")
    @ResponseBody
    public Map<String,Object> getTree(HttpServletRequest request, HttpServletResponse response){
        return null;
    }

    /**
     * 获取轨迹里程统计
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getTrack.do")
    @ResponseBody
    public Map<String,Object> getTrack(HttpServletRequest request, HttpServletResponse response){
        return null;
    }

    /**
     * 获取事件数量统计
     * @return
     */
    @RequestMapping("/getEvent.do")
    @ResponseBody
    public Map<String,Object> getEvent(HttpServletRequest request, HttpServletResponse response){
        return null;
    }
}
