package com.boonya.sbsqlite.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.SQLiteDataSource;
import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class DynamicDataSource {

    public static final String SQLite="jdbc:sqlite:";

    /**
     * 获取数据库文件路径
     * @return
     */
    public static List<String> getDatabaseRoutes(String dbFilePath,Boolean hasPrefix){
        List<String> list=new ArrayList<String>();
        File file=new File(dbFilePath);
        if(file.exists()){
           File [] files= file.listFiles();
            for(File f: files){
                if(!f.getPath().endsWith(".db")){
                    continue;
                }
                String route=hasPrefix?(SQLite+f.getPath()):f.getPath();
                list.add(route);
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
