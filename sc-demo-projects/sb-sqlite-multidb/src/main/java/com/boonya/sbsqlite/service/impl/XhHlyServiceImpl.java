package com.boonya.sbsqlite.service.impl;

import com.boonya.sbsqlite.config.DataSourceConfig;
import com.boonya.sbsqlite.service.XhHlyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class XhHlyServiceImpl implements XhHlyService {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public Map<String,Object> select(long id) {
        String sql="SELECT * FROM XH_HLY_TB_ZB WHERE OBJECTID="+id;
        return jdbcTemplate.queryForList(sql).get(0);
    }

    public Map<String,Object> select(String number) {
        String sql="SELECT * FROM XH_HLY_TB_ZB WHERE USER_TEL='"+number+"'";
        return jdbcTemplate.queryForList(sql).get(0);
    }

    public Map<String,Object> select(String number,String dwCode) {
        String sql="SELECT * FROM XH_HLY_TB_"+dwCode+" WHERE USER_TEL='"+number+"'";
        return jdbcTemplate.queryForList(sql).get(0);
    }

    public List<Map<String,Object>> selectAll() {
        String sql="select m.* from xh_hly_tb_zb m inner join  ("
                +"SELECT a.HLY_ID	FROM"
                +"	("
                +"	   select HLY_ID,count(HLY_ID) NUMBER from xh_hly_tb_zb  group by HLY_ID order by NUMBER DESC"
                +"	) a WHERE a.NUMBER>1 "
                +") n  "
                +"on m.hly_id=n.hly_ID ORDER BY m.HLY_ID ASC";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String,Object>> selectRepairList() {
        String sql="SELECT * FROM XH_DATA_REPAIR ORDER BY HLY_ID ASC";
        return jdbcTemplate.queryForList(sql);
    }

    public boolean hasHandledNumber(String number){
        String sql="SELECT * FROM XH_DATA_NUMBER WHERE number='"+number+"'";
        List<Map<String,Object>>  result= jdbcTemplate.queryForList(sql);
        if(null==result||result.size()==0){
            return false;
        }
        return true;
    }

    public long getCurrentHlyId(){
        String sql="SELECT current_val FROM fs_sequence WHERE seq_name='XH_HLY_TB_ZB'";
        return Long.valueOf(jdbcTemplate.queryForList(sql).get(0).get("current_val").toString());
    }

    @Transactional(rollbackFor =Exception.class )
    public boolean updateSequenceHlyId(String newHlyId) throws Exception{
        String sql="UPDATE fs_sequence SET current_val="+newHlyId+"  WHERE seq_name='XH_HLY_TB_ZB'";
        try{
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional(rollbackFor =Exception.class )
    public boolean updateRepairHlyId(String number,String newHlyId) throws Exception{
        String sql="UPDATE XH_DATA_REPAIR SET HLY_ID_NEW='"+newHlyId+"'  WHERE USER_TEL='"+number+"'";
        try{
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional(rollbackFor =Exception.class )
    public boolean updateHlyZbId(String number,String newHlyId) throws Exception{
        String sql="UPDATE XH_HLY_TB_ZB SET HLY_ID='"+newHlyId+"'  WHERE USER_TEL='"+number+"'";
        try{
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional(rollbackFor =Exception.class )
    public boolean updateHlyFbId(String number,String dwCode,String newHlyId) throws Exception{
        String sql="UPDATE XH_HLY_TB_"+dwCode+" SET HLY_ID='"+newHlyId+"'  WHERE USER_TEL='"+number+"'";
        try{
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional(rollbackFor =Exception.class )
    public boolean saveHandledNumber(String number) throws Exception{
        String sql="INSERT INTO  XH_DATA_NUMBER(NUMBER) VALUES('"+number+"')";
        try{
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional(rollbackFor =Exception.class )
    public boolean updateAllData(String number){
        try{
            // 是否含有分表
            boolean hasFb=false;
            String dwCode="";
            Map<String,Object> zbItem=this.select(number);
            if(null!=zbItem.get("DW_CODE")&&!"".equals(zbItem.get("DW_CODE").toString().trim())){
                hasFb=true;
                dwCode=zbItem.get("DW_CODE").toString();
            }
            // 获取更新的ID值
            long newHlyId= this.getCurrentHlyId()+1;
            logger.info("最新巡护员ID={}",newHlyId);
            // 更新自增序列
            this.updateSequenceHlyId(newHlyId+"");
            // 更新映射表记录
            this.updateRepairHlyId(number,newHlyId+"");
            // 更新主表记录
            this.updateHlyZbId(number,newHlyId+"");
            // 更新分表记录
            if(hasFb){
                this.updateHlyFbId(number,dwCode,newHlyId+"");
            }
            // 保存手机号处理记录
            this.saveHandledNumber(number);
            logger.info("{}处理成功ID={}",number,newHlyId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public  Map<String,Object> repairZbHlyIds() {
        Map<String,Object> map=new HashMap<String,Object>();
        List<String> numbers=new ArrayList<String>();
        // 获取所有重复记录
        List<Map<String,Object>> list= this.selectRepairList();
        for (Map<String,Object> m:list ) {
            if(!map.keySet().contains(m.get("HLY_ID").toString())){
                map.put(m.get("HLY_ID").toString(),m.get("HLY_ID").toString());
            }else{
                numbers.add(m.get("USER_TEL").toString());
            }
        }
        AtomicInteger success=new AtomicInteger(0);
        AtomicInteger real=new AtomicInteger(0);
        AtomicInteger failed=new AtomicInteger(0);
        // 更新ID编码
        if(null!=numbers&&numbers.size()>0){
            for (String number:numbers) {
                boolean  handled=this.hasHandledNumber(number);
                if(handled){
                    success.incrementAndGet();
                }else{
                    boolean flag=this.updateAllData(number);
                    if(flag){
                        real.incrementAndGet();
                    }else{
                        failed.incrementAndGet();
                    }
                }
            }
        }
        Map<String,Object> result=new HashMap<String,Object>();
        result.put("update_numbers",numbers);
        result.put("success",success);
        result.put("real",real);
        result.put("failed",failed);
        return result;
    }

    @Override
    public  Map<String,Object> repairFbHlyIds() {
        // 手动执行处理，暂不用代码处理
        return null;
    }

    @Override
    public boolean hasRepairedNumber(String number){
        String sql="SELECT * FROM XH_DATA_REPAIR WHERE USER_TEL='"+number+"' and  REPAIRED=1 ";
        List<Map<String,Object>>  result= jdbcTemplate.queryForList(sql);
        if(null!=result||result.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRepairedStatus(String number){
        String sql="UPDATE XH_DATA_REPAIR set REPAIRED=1 WHERE USER_TEL='"+number+"'";
        try{
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
