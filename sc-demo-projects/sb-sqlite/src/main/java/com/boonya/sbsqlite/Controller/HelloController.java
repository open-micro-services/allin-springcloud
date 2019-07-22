package com.boonya.sbsqlite.Controller;

import com.boonya.sbsqlite.Model.HelloModel;
import com.boonya.sbsqlite.Model.ReqBody;
import com.boonya.sbsqlite.Service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
public class HelloController {

    private final HelloService HelloService;

    @Autowired
    public HelloController(HelloService HelloService) {
        this.HelloService = HelloService;
    }

    @RequestMapping("/")
    public String Index() {
        return "Hello World";
    }

    @RequestMapping("/list")
    public List<HelloModel> List() {
        return HelloService.selectAll();
    }

    @RequestMapping("/save")
    public boolean save(HelloModel model) {
        return HelloService.insert(model);
    }

    @RequestMapping("/delete")
    public List<HelloModel> delete() {
        return HelloService.selectAll();
    }

    @RequestMapping("/update")
    public boolean update(HelloModel model) {
        return HelloService.updateValue(model);
    }

    @RequestMapping("/{id}")
    public HelloModel getById(@PathVariable("id") long id) {
        return HelloService.select(id);
    }

    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public String email(@RequestBody ReqBody map) throws  IOException {
        return "输入的姓名是" + map.getName() + ",电子邮件是:" + map.getEmail();
    }
}
