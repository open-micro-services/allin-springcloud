package com.boonya.sbsqlite.service.impl;

import com.boonya.sbsqlite.config.DataSourceConfig;
import com.boonya.sbsqlite.config.DynamicDataSource;
import com.boonya.sbsqlite.service.RepairService;
import com.boonya.sbsqlite.service.XhHlyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RepairServiceImpl implements RepairService {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    XhHlyService xhHlyService;

    /*------------------------------用户管理---------------------*/
    public Map<String,Object> selectUser(String dbUrl,String number) {
        String sql="SELECT * FROM TABLE_XHUSER where user_phone_num='"+number+"'";
        DataSource dataSource=DynamicDataSource.getSQLiteDataSource(dbUrl);
        List<Map<String,Object>> list= DynamicDataSource.getJdbcTemplateByDataSource(dataSource).queryForList(sql);
        if(null!=list&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
    public List<Map<String,Object>> selectUserAll(String dbUrl) {
        String sql="SELECT * FROM TABLE_XHUSER";
        DataSource dataSource=DynamicDataSource.getSQLiteDataSource(dbUrl);
        return DynamicDataSource.getJdbcTemplateByDataSource(dataSource).queryForList(sql);
    }
    /*------------------------------事件管理---------------------*/
    public List<Map<String,Object>> selectBusinessAll(String dbUrl) {
        String sql="SELECT * FROM TableBusiness";
        DataSource dataSource=DynamicDataSource.getSQLiteDataSource(dbUrl);
        return DynamicDataSource.getJdbcTemplateByDataSource(dataSource).queryForList(sql);
    }

    public List<Map<String,Object>> matchBusinessUniqueRecords(String dbUrl, String hlyId) {
        List<Map<String,Object>> list=this.selectBusinessAll(dbUrl);
        return null;
    }

    public boolean updateBusinessForNewHlyId(String dbUrl, String hlyId){
        return false;
    }
    /*------------------------------通知公告---------------------*/
    public List<Map<String,Object>> selectNoticeAll(String dbUrl) {
        String sql="SELECT * FROM TABLE_NOTICE";
        DataSource dataSource=DynamicDataSource.getSQLiteDataSource(dbUrl);
        return DynamicDataSource.getJdbcTemplateByDataSource(dataSource).queryForList(sql);
    }

    public List<Map<String,Object>> matchNoticeUniqueRecords(String dbUrl,String hlyId) {
        return null;
    }

    public boolean updateNoticeForNewHlyId( String dbUrl, String hlyId){
        return false;
    }
    /*------------------------------轨迹点数据---------------------*/
    public List<Map<String,Object>> selectTrackAll(String dbUrl) {
        String sql="SELECT * FROM TABLE_XHTRACK_POINT";
        DataSource dataSource=DynamicDataSource.getSQLiteDataSource(dbUrl);
        return DynamicDataSource.getJdbcTemplateByDataSource(dataSource).queryForList(sql);
    }
    public List<Map<String,Object>> matchTrackUniqueRecords(String dbUrl,String hlyId) {
        return null;
    }

    public boolean updateTrackForNewHlyId( String dbUrl, String hlyId){
        return false;
    }
    /*------------------------------工作日志---------------------*/
    public List<Map<String,Object>> selectWorkLogAll(String dbUrl) {
        String sql="SELECT * FROM TABLE_WORKLOG";
        DataSource dataSource=DynamicDataSource.getSQLiteDataSource(dbUrl);
        return DynamicDataSource.getJdbcTemplateByDataSource(dataSource).queryForList(sql);
    }

    public List<Map<String,Object>> matchWorkLogUniqueRecords(String dbUrl,String hlyId) {
        return null;
    }

    public boolean updateWorkLogForNewHlyId(String dbUrl, String hlyId){
        return false;
    }

    /*------------------------------修复处理---------------------*/
    @Transactional(rollbackFor = Exception.class)
    public boolean repairServiceDataForNumber(String dbUrl,String number){
        boolean repaired=xhHlyService.hasRepairedNumber(number);
        if(repaired){
            logger.info("{}业务数据已经修复过了,中止执行!",number);
            return true;
        }
        // 获取用户信息
        Map<String,Object> user=this.selectUser(dbUrl,number);
        if(null==user){
            return false;
        }
        String hlyId=user.get("user_id").toString();
        try{
            // 更新事件数据
            this.updateBusinessForNewHlyId(dbUrl,hlyId);
            // 更新日志数据
            this.updateWorkLogForNewHlyId(dbUrl,hlyId);
            // 更新通知公告
            this.updateNoticeForNewHlyId(dbUrl,hlyId);
            // 更新轨迹数据
            this.updateTrackForNewHlyId(dbUrl,hlyId);

            // 修改更新状态
            xhHlyService.updateRepairedStatus(number);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public  Map<String,Object> repairServiceDataForAll(List<String> connections){
        Map<String,Object> result=new HashMap<String,Object>();
        if(null==connections||connections.size()==0){
            result.put("msg","数据连接为空,请检查配置的SQLite数据库文件路径是否正确");
            return result;
        }
        AtomicInteger success=new AtomicInteger(0);
        AtomicInteger real=new AtomicInteger(0);
        AtomicInteger failed=new AtomicInteger(0);
        for (String dbUrl:   connections  ) {
            String number=dbUrl.split("_")[0];
            // 修复手机号数据
            boolean repaired=xhHlyService.hasRepairedNumber(number);
            if(repaired){
                success.incrementAndGet();
            }else{
                boolean flag=this.repairServiceDataForNumber(dbUrl,number);
                if(flag){
                    real.incrementAndGet();
                }else{
                    failed.incrementAndGet();
                }
            }
        }
        result.put("connections",connections);
        result.put("success",success);
        result.put("real",real);
        result.put("failed",failed);
        return result;
    }

}