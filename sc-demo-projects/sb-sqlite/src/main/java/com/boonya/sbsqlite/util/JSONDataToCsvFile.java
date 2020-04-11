package com.boonya.sbsqlite.util;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JSON数据存储为csv文件
 */
public class JSONDataToCsvFile {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public static String getCsvData(JSONArray jsonArray){
        return CDL.toString(jsonArray);
    }

    public static void  saveJson2Csv(JSONArray jsonArray) throws JSONException {
        String fileName = "data_repair_table_" + sdf.format(new Date());
        String cvsData = getCsvData(jsonArray);
        saveJson2Csv(fileName,cvsData);
    }

    public static void  saveJson2Csv(String fileName,String cvsData) throws JSONException {
        File file = new File(DynamicDataSource.dbsBasePath+"/"+fileName+".csv");
        try {
            FileUtils.writeStringToFile(file,cvsData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
