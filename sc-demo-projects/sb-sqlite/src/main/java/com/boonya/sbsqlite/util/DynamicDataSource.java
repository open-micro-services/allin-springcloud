package com.boonya.sbsqlite.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.SQLiteDataSource;
import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DynamicDataSource {

    public static  String dbsBasePath;

    /**
     * 获取数据库文件路径
     * @return
     */
    public static List<String> getDatabaseRoutes(){
        List<String> list = new ArrayList<>();
        File file = new File(dbsBasePath);
        File [] files = file.listFiles();
        if(files.length>0){
            for (File file_:  files ) {
                String name = file_.getName();
                if(name.contains(".db")){
                    name = "jdbc:sqlite:"+dbsBasePath+"/"+name;
                    System.out.println(name);
                    list.add(name);
                }
            }
        }
        return list;
    }

    /**
     * 动态设置SQLite数据源地址访问
     * @param url
     * @return
     */
    public static DataSource getSQLiteDataSource(String url){
        SQLiteDataSource sqLiteDataSource=new SQLiteDataSource();
        sqLiteDataSource.setUrl(url);
        return sqLiteDataSource;
    }

    /**
     * 动态设置SQLite数据源地址访问
     * @param dataSource
     * @return
     */
    public static JdbcTemplate getJdbcTemplateByDataSource(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
