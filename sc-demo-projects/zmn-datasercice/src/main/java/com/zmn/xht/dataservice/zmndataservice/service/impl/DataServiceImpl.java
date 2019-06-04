package com.zmn.xht.dataservice.zmndataservice.service.impl;

import com.zmn.xht.dataservice.zmndataservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String getNSJGTreeByDwCode(String dwCode) {
        return null;
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
