package com.boonya.springboot.mybatis.sbmybatis.controller;

import com.boonya.springboot.mybatis.sbmybatis.entity.User;
import com.boonya.springboot.mybatis.sbmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    @RequestMapping("/deleteByPrimaryKey")
    @ResponseBody
    public Map<String,Object> deleteByPrimaryKey(Integer id){
       Map<String,Object> map=new HashMap<String,Object>();
       try{
           boolean flag=userService.deleteByPrimaryKey(id);
           map.put("status",flag?1:0);
           map.put("msg",flag?"成功":"失败");
       }catch (Exception e){
           map.put("status",0);
           map.put("msg","失败");
       }
       return map;
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    @ResponseBody
    public Map<String,Object>  saveUser(User user){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            boolean flag=userService.insert(user);
            map.put("status",flag?1:0);
            map.put("msg",flag?"成功":"失败");
        }catch (Exception e){
            map.put("status",0);
            map.put("msg","失败");
        }
        return map;
    }

    /**
     * 按需保存用户字段
     * @param user
     * @return
     */
    @RequestMapping("/saveUserSelective")
    @ResponseBody
    public Map<String,Object>  saveUserSelective(User user){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            boolean flag=userService.insertSelective(user);
            map.put("status",flag?1:0);
            map.put("msg",flag?"成功":"失败");
        }catch (Exception e){
            map.put("status",0);
            map.put("msg","失败");
        }
        return map;
    }

    /**
     * 根据主键查询用户
     * @param id
     * @return
     */
    @RequestMapping("/selectByPrimaryKey")
    @ResponseBody
    public User selectByPrimaryKey(Integer id){
        try{
            return userService.selectByPrimaryKey(id);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 更新指定字段的用户信息
     * @param user
     * @return
     */
    @RequestMapping("/updateByPrimaryKeySelective")
    @ResponseBody
    public Map<String,Object> updateByPrimaryKeySelective(User user){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            boolean flag=userService.updateByPrimaryKeySelective(user);
            map.put("status",flag?1:0);
            map.put("msg",flag?"成功":"失败");
        }catch (Exception e){
            map.put("status",0);
            map.put("msg","失败");
        }
        return map;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping("/updateByPrimaryKey")
    @ResponseBody
    public Map<String,Object> updateByPrimaryKey(User user){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            boolean flag=userService.updateByPrimaryKey(user);
            map.put("status",flag?1:0);
            map.put("msg",flag?"成功":"失败");
        }catch (Exception e){
            map.put("status",0);
            map.put("msg","失败");
        }
        return map;
    }

}
