package com.boonya.mysql.multidb.datasource;

import com.boonya.mysql.multidb.util.DBDataSourceHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName: DBRoutingDataSource
 * @Description: TODO(功能说明:Spring源码提供的数据库连接池路由功能)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/15 0:26
 */
public class DBRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DBDataSourceHolder.getDbDataSourceHolderType();
    }
}
