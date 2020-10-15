package com.boonya.sbsqlite.controller;

import com.boonya.sbsqlite.service.XhBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/business.do")
public class XhBusinessController {
    @Autowired
    private XhBusinessService xhBusinessService;

    @RequestMapping("/list.do")
    public List< Map<String,Object>> getList() {
        return xhBusinessService.selectAll();
    }

    @RequestMapping("/{id}")
    public Map<String,Object> getById(@PathVariable("id") long id) {
        return xhBusinessService.select(id);
    }

}
