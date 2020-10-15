package com.boonya.sbsqlite.Controller;

import com.boonya.sbsqlite.entity.XhWorkLog;
import com.boonya.sbsqlite.service.XhWorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/workLog")
public class XhWorkLogController {
    @Autowired
    private  XhWorkLogService xhWorkLogService;

    @RequestMapping("/list.do")
    public List<Map<String,Object>> getList() {
        return xhWorkLogService.selectAll();
    }

    @RequestMapping("/{id}")
    public Map<String,Object> getById(@PathVariable("id") long id) {
        return xhWorkLogService.select(id);
    }

}
