package com.boonya.mysql.multidb.util;

import com.boonya.mysql.multidb.bean.DataSourceType;

/**
 * @ClassName: DBDataSourceHolder
 * @Description: TODO(功能说明:使用本地化线程持有数据库数据源)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/15 0:29
 */
public class DBDataSourceHolder {

    private static final ThreadLocal<DataSourceType> dbDataSourceHolder=new ThreadLocal<>();

    public static DataSourceType getDbDataSourceHolderType() {
        return dbDataSourceHolder.get();
    }

    public static void setDbDataSourceHolder(DataSourceType dataSourceType) {
        dbDataSourceHolder.set(dataSourceType);
    }

    public static void master() {
        dbDataSourceHolder.set(DataSourceType.MASTER);
    }

    public static void slave() {
        dbDataSourceHolder.set(DataSourceType.SLAVE);
    }

}
