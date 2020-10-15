package com.boonya.sbsqlite.Controller;

import com.boonya.sbsqlite.entity.XhNotice;
import com.boonya.sbsqlite.service.XhNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/notice")
public class XhNoticeController {
    @Autowired
    private  XhNoticeService xhNoticeService;

    @RequestMapping("/list.do")
    public List< Map<String,Object> > getList() {
        return xhNoticeService.selectAll();
    }


    @RequestMapping("/{id}")
    public Map<String,Object> getById(@PathVariable("id") long id) {
        return xhNoticeService.select(id);
    }

}
