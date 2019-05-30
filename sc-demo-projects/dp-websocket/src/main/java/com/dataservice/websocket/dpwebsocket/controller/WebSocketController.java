package com.dataservice.websocket.dpwebsocket.controller;

import com.dataservice.websocket.dpwebsocket.util.JSONUtil;
import com.dataservice.websocket.dpwebsocket.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * WebSocket对外操作接口
 */
@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 推送数据接口
     *
     * @param clientId
     * @param message
     * @return
     */
    @RequestMapping(value = "/push/{clientId}")
    @ResponseBody
    public Map<String, Object> pushMessageToPage(@PathVariable("clientId") String clientId, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 封装消息体
            Map<String, Object> data =new HashMap<String, Object>();
            data.put("clientId", clientId);
            data.put("message", message);
            // 推送JSON消息
            WebSocketServer.broadcastOne(JSONUtil.getJsonFromObject(data), clientId);
            // 封装返回结构体
            map.put("status", 1);
            map.put("msg", "消息推送成功!");
            map.put("data", data);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", "消息推送失败!");
            map.put("data", new HashMap<String, Object>());
            return map;
        }
        return map;
    }

    /**
     * 获取页面连接客户端列表
     * @return
     */
    @RequestMapping(value = "/connection/clients")
    @ResponseBody
    public Map<String, Object> getClients() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> clients= WebSocketServer.getClients();
        if(clients.size()>0){
            map.put("status", 1);
            map.put("msg", "获取客户端连接标识成功!");
            map.put("data", clients);
        }else{
            map.put("status", 0);
            map.put("msg", "获取客户端连接标识失败!");
            map.put("data", new HashMap<String, Object>());
        }
        return map;
    }

    /**
     * 获取用户信息
     * @param hlyId
     * @return
     */
    @RequestMapping(value = "hlyInfo.do")
    @ResponseBody
    public  String getHlyInfo(@RequestParam("hlyId") String hlyId){
        String sql="SELECT c.id,c.`name`,d.C_DESCRIBE nation,c.companycode,c.company from "
            +"(SELECT a.HLY_ID id,a.USER_XM name,a.USER_MZ nation,a.DW_CODE companycode,b.ZC_NAME company "
            + "FROM xh_hly_tb_zb a,xh_zc_tb b WHERE a.DW_CODE=b.DW_CODE AND a.HLY_ID="
            + "'"+hlyId+"'"
            +") c LEFT JOIN xht_nation d ON c.nation=d.C_CODE";
        try {
            List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
            if(list!=null){
                return WebSocketServer.getFeedbackMessage(1,"查询护林员信息成功",list.get(0));
            }else{
                return WebSocketServer.getFeedbackMessage(0,"没有查询到护林员信息",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return WebSocketServer.getFeedbackMessage(0,"查询护林员消息异常",null);
        }
    }

}
