package com.boonya.sbsqlite.service;

import java.util.List;
import java.util.Map;

public interface XhWorkLogService {

    /**
     * 查询数据库ID记录
     * @param id
     * @return
     */
    public Map<String,Object> select(long id);

    /**
     * 查询数据库所有记录
     * @return
     */
    public List<Map<String,Object>> selectAll() ;
}