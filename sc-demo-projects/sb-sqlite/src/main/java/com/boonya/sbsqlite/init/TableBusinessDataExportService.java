package com.boonya.sbsqlite.init;

import com.boonya.sbsqlite.util.DynamicDataSource;
import com.boonya.sbsqlite.util.JSONDataToCsvFile;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 批量处理sqlite数据库文件数据
 */
@Service
public class TableBusinessDataExportService {

    @Value("${system.initExportCVSFile}")
    private boolean initExportCVSFile;

    @Value("${system.dbsBasePath}")
    private String dbsBasePath;

    public List<Map<String,Object>> selectBusinessAll( JdbcTemplate jdbcTemplate) {
        String sql="SELECT * FROM TableBusiness where key='XH_DCTBHS_TB'";
        return jdbcTemplate.queryForList(sql);
    }

    @PostConstruct
    public void selectAll() {
        DynamicDataSource.dbsBasePath = dbsBasePath;
        if(!initExportCVSFile){
            System.out.println("=============initExportCVSFile======false=========");
            return ;
        }
        List<String> list = DynamicDataSource.getDatabaseRoutes();
        DataSource dataSource = null;
        JdbcTemplate jdbcTemplate = null;
        JSONArray jsonArray = new JSONArray();
        for (int i = 0 ,j = list.size(); i < j; i++) {
            String url = list.get(i);
            dataSource = DynamicDataSource.getSQLiteDataSource(url);
            jdbcTemplate = DynamicDataSource.getJdbcTemplateByDataSource(dataSource);

            List<Map<String,Object>> mapList= selectBusinessAll(jdbcTemplate);
            System.out.println(JSONObject.valueToString(mapList));
            if(mapList.size()>0){
                JSONObject item= null ;
                JSONObject jsonMap=null;
                for ( Map<String,Object> map: mapList ) {
                    item = new JSONObject();
                    item.put("num",map.get("num"));
                    jsonMap= new JSONObject(map.get("json").toString());
                    item.put("mianji",jsonMap.get("MIAN_JI"));
                    Double x = 0.0,y=0.0;
                    try{
                        x = jsonMap.getDouble("X");
                    }catch (Exception e){
                        x =jsonMap.getDouble("SJ_WEIZHI_X");
                    }
                    try{
                        y = jsonMap.getDouble("Y");
                    }catch (Exception e){
                        y =jsonMap.getDouble("SJ_WEIZHI_Y");
                    }
                    item.put("x",x);
                    item.put("y",y);
                    jsonArray.put(item);
                }
            }
        }
        JSONDataToCsvFile.saveJson2Csv(jsonArray);
    }


}