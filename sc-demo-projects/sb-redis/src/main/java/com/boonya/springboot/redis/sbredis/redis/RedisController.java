package com.boonya.springboot.redis.sbredis.redis;

import com.boonya.springboot.redis.sbredis.util.RedisNodeManagerUtil;
import com.boonya.springboot.redis.sbredis.util.RedisUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@RestController
@RequestMapping("/redis")
public class RedisController {

    private String redis="redis:test";

    @RequestMapping("/{node}/set/{key}/{value}")
    public Map<String,Object> setKeyValue(@PathVariable("node") String node, @PathVariable("key") String key, @PathVariable("value") String value){
       Map<String,Object> map=new HashMap<String,Object>();
       try {
           RedisNodeManagerUtil.set(node,redis+":"+key,value);
           map.put("status",1);
           map.put("msg","操作成功!:");
       }catch (Exception e){
           map.put("status",0);
           map.put("msg","操作异常:"+e.getMessage());
       }
       return map;
    }

    @RequestMapping("/{node}/get/{key}")
    public  Map<String,Object>  getValueByKey(@PathVariable("node") String node,@PathVariable("key") String key){
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            String data=RedisNodeManagerUtil.get(node,redis+":"+key);
            map.put("data",data);
            map.put("status",1);
            map.put("msg","操作成功!:");
        }catch (Exception e){
            map.put("status",0);
            map.put("msg","操作异常:"+e.getMessage());
        }
        return map;
    }

    @RequestMapping("/{node}/keys")
    public  Set<String> getKeys(@PathVariable("node") String node){
        Set<String> keys = RedisNodeManagerUtil.keys(node,redis+":*");
        return keys;
    }
}
