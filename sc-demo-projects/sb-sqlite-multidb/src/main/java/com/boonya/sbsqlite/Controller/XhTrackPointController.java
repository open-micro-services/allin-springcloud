package com.boonya.sbsqlite.Controller;

import com.boonya.sbsqlite.entity.XhTrackPoint;
import com.boonya.sbsqlite.service.XhTrackPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/trackPoint")
public class XhTrackPointController {
    @Autowired
    private  XhTrackPointService xhTrackPointService;

    @RequestMapping("/list.do")
    public List< Map<String,Object>> getList() {
        return xhTrackPointService.selectAll();
    }

    @RequestMapping("/{id}")
    public Map<String,Object> getById(@PathVariable("id") long id) {
        return xhTrackPointService.select(id);
    }

}
