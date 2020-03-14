package com.boonya.mysql.multidb.config;

import com.boonya.mysql.multidb.bean.DataSourceType;
import com.boonya.mysql.multidb.datasource.DBRoutingDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DataSourceConfig
 * @Description: TODO(功能说明:定义数据源配置类型和数据源路由配置)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/15 0:38
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource dbRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,@Qualifier("slaveDataSource") DataSource slaveDataSource){
        DBRoutingDataSource dbRoutingDataSource=new DBRoutingDataSource();
        // 保存数据源配置
        Map<Object,Object> targetDataSources=new HashMap<>(2);
        targetDataSources.put(DataSourceType.MASTER,masterDataSource);
        targetDataSources.put(DataSourceType.SLAVE,slaveDataSource);
        dbRoutingDataSource.setTargetDataSources(targetDataSources);
        // 设定是数据源缺省默认使用
        dbRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        return dbRoutingDataSource;
    }
}
