package com.zmn.xht.dataservice.zmndataservice.service.impl;

import com.zmn.xht.dataservice.zmndataservice.service.DataService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String getNSJGTreeByDwCode(String dwCode) {
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> nsjgList=jdbcTemplate.queryForList("SELECT * FROM XH_NSJG_TB_"+dwCode+" where 1=1 ORDER BY NSJGID ASC");
        List<Map<String,Object>> hlyList=jdbcTemplate.queryForList("SELECT * FROM XH_HLY_TB_"+dwCode+" where 1=1 ORDER BY NSJGID ASC");
        Map<String,Object> nsgj=null;
        Map<String,Object> hly=null;
        for (int i = 0,j=nsjgList.size(); i < j; i++) {
            nsgj=new HashMap<String,Object>();
            nsgj.put("id",nsjgList.get(i).get("NSJGID"));
            nsgj.put("name",nsjgList.get(i).get("NAME"));
            nsgj.put("value",nsjgList.get(i).get("LXFS"));
            nsgj.put("parentId",nsjgList.get(i).get("PARID"));
            list.add(nsgj);
        }
        for (int i = 0,j=hlyList.size(); i < j; i++) {
            hly=new HashMap<String,Object>();
            hly.put("id",hlyList.get(i).get("HLY_ID"));
            hly.put("name",hlyList.get(i).get("USER_XM"));
            hly.put("value",hlyList.get(i).get("NSJGID"));
            hly.put("parentId",hlyList.get(i).get("NSJGID"));
            list.add(hly);
        }
        return JSONArray.fromObject(list).toString();
    }

    @Override
    public String getNSJGByDwCode(String dwCode){
        List<Map<String,Object>> list=jdbcTemplate.queryForList("SELECT * FROM XH_NSJG_TB_"+dwCode+" where 1=1 ORDER BY NSJGID ASC");
        return JSONArray.fromObject(list).toString();
    }

    @Override
    public String getHlyByDwCode(String dwCode){
        List<Map<String,Object>> list=jdbcTemplate.queryForList("SELECT * FROM XH_HLY_TB_"+dwCode+" where 1=1 ORDER BY NSJGID ASC");
        return JSONArray.fromObject(list).toString();
    }

    @Override
    public String getHlyTrackFactorsByDwCode(String dwCode, Date start, Date end) {
        return null;
    }

    @Override
    public String getHlyEventFactorsByDwCode(String dwCode, Date start, Date end) {
        return null;
    }

    @Override
    public boolean saveDwTrackFactorsTree(int type, int year, int month, int week, Date day, String dwCode, String jsonData) {
        return false;
    }

    @Override
    public boolean saveDwEventFactorsTree(int type, int year, int month, int week, Date day, String dwCode, String jsonData) {
        return false;
    }

    @Override
    public boolean updateDwTrackFactorsTree(String objectId, String jsonData) {
        return false;
    }

    @Override
    public boolean updateDwEventFactorsTree(String objectId, String jsonData) {
        return false;
    }

    @Override
    public String getDwTrackFactorsTree(int type, int year, int month, int week, Date day, String dwCode) {
        return null;
    }

    @Override
    public String getDwEventFactorsTree(int type, int year, int month, int week, Date day, String dwCode) {
        return null;
    }
}
