package com.boonya.springboot.jdbc.sbjdbc.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class JdbcTemplateController {

    @Autowired(required = true)
    JdbcTemplate jdbcTemplate;

    @GetMapping("/list")
    @ResponseBody
    public List<Map<String, Object>> query() {
        System.out.println("" + jdbcTemplate.getDataSource());
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from xht_events order by xiancode limit 0,10");
        return list;
    }
}