package com.boonya.sbsqlite.Controller;

import com.boonya.sbsqlite.service.XhHlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/hly")
public class XhHlyController {
    @Autowired
    private  XhHlyService xhHlyService;

    /**
     * 主表原始记录重复查询
     * @return
     */
    @RequestMapping("/originList.do")
    public List<Map<String,Object>> getOriginList() {
        return xhHlyService.selectAll();
    }

    /**
     * 中间映射表记录重复查询
     * @return
     */
    @RequestMapping("/repairList.do")
    public List<Map<String,Object>> getRepairList() {
        return xhHlyService.selectRepairList();
    }

    /**
     * 索引主键ID查询
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public  Map<String,Object>  getById(@PathVariable("id") long id) {
        return xhHlyService.select(id);
    }

    /**
     * 修复巡护员主表ID操作
     * @return
     */
    @RequestMapping("/repairZbHlyIds.do")
    public  Map<String,Object>  repairZbHlyIds() {
        return xhHlyService.repairZbHlyIds();
    }

    /**
     * 修复巡护员分表ID操作
     * @return
     */
    @RequestMapping("/repairFbHlyIds.do")
    public  Map<String,Object>  repairFbHlyIds() {
        return xhHlyService.repairZbHlyIds();
    }

}
