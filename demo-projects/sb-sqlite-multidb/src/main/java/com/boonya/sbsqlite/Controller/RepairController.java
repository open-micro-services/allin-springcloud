package com.boonya.sbsqlite.controller;

import com.boonya.sbsqlite.config.DynamicDataSource;
import com.boonya.sbsqlite.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    RepairService repairService;

    @Value("${dbFilePath}")
    private  String dbFilePath;

    @RequestMapping("/getDatabasePath.do")
    public String getDatabasePath(){
        return dbFilePath;
    }

    @RequestMapping("/getDatabaseRoutes.do")
    public List<String> getDatabaseRoutes(){
        return DynamicDataSource.getDatabaseRoutes(dbFilePath,true);
    }

    @RequestMapping("/getDatabasePaths.do")
    public List<String> getDatabasePaths(){
        return DynamicDataSource.getDatabaseRoutes(dbFilePath,false);
    }

    /**
     * 测试动态数据库连接是否正常
     * @return
     */
    @RequestMapping("/getConnection.do")
    public  List<Map<String,Object>> getConnection(){
        List<String> connections=this.getDatabaseRoutes();
        String dbUrl=connections.get(0);
        return repairService.selectUserAll(dbUrl) ;
    }

    /**
     * 修复指定护林员数据
     * @return
     */
    @RequestMapping("/repairServiceDataForNumber.do")
    public  Map<String,Object> repairServiceDataForNumber(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> result=new HashMap<String,Object>();
        String number="",destNumber="",destDbUrl="";
        number=request.getParameter("number");
        if(null==number||"".equals(number)){
            result.put("msg","请传入number参数对应的手机号");
            return  result;
        }
        List<String> connections=this.getDatabaseRoutes();
        if(null==connections||connections.size()==0){
            result.put("msg","没有找到数据库连接，请检查SQLite数据库对应的dbFilePath配置路径是否正确");
            return  result;
        }
        List<String> paths=this.getDatabasePaths();
        for (String path:paths) {
            if(!"".equals(path)&&path.endsWith(".db")&&path.contains(number)){
                File file=new File(path);
                if(file.exists()){
                    destDbUrl=DynamicDataSource.SQLite+path;
                    destNumber=file.getName().split("_")[0];
                    break;
                }
            }
        }
        if("".equals(destNumber)){
            result.put("msg","传入的手机号暂未找到对应的数据文件");
            return  result;
        }
        result.put("number",destNumber);
        boolean success=repairService.repairServiceDataForNumber(destDbUrl,destNumber);
        result.put("success",success);
        return  result;
    }

    /**
     * 修复全部业务数据
     * @return
     */
    @RequestMapping("/repairServiceDataForAll.do")
    public Map<String,Object> repairServiceDataForAll(){
        Map<String,Object> result=new HashMap<String,Object>();
        List<String> connections=this.getDatabaseRoutes();
        if(null==connections||connections.size()==0){
            result.put("msg","没有找到数据库连接，请检查SQLite数据库对应的dbFilePath配置路径是否正确");
            return  result;
        }
        return  repairService.repairServiceDataForAll(connections);
    }
}
