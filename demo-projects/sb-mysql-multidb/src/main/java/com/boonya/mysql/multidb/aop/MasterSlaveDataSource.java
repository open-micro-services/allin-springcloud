package com.boonya.mysql.multidb.aop;

import com.boonya.mysql.multidb.util.DBDataSourceHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MasterSlaveDataSource
 * @Description: TODO(功能说明:Spring AOP切面处理读写分离数据源切换)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/15 1:02
 */
@Aspect
@Component
public class MasterSlaveDataSource {

    /**
     * Mysql主库写入切面配置@DBMaster强制使用主库
     */
    @Pointcut("@annotation(com.boonya.mysql.multidb.annotation.DBMaster)"
        +"&&(execution(* com.*.service..*.save*(..))"
        +"||execution(* com.*.service..*.add*(..))"
        +"||execution(* com.*.service..*.insert*(..))"
        +"||execution(* com.*.service..*.edit*(..))"
        +"||execution(* com.*.service..*.update*(..))"
        +"||execution(* com.*.service..*.delete*(..))"
        +")")
    public void masterPointCut(){

    }

    /**
     * Mysql从库库读取查询
     */
    @Pointcut("!@annotation(com.boonya.mysql.multidb.annotation.DBMaster)"
        +"&&(execution(* com.*.service..*.get*(..))"
        +"||execution(* com.*.service..*.select*(..))"
        +"||execution(* com.*.service..*.find*(..))"
        +"||execution(* com.*.service..*.query*(..))"
        +")")
    public void slavePointCut(){

    }

    @Before("masterPointCut()")
    public void master(){
        DBDataSourceHolder.master();
    }

    @Before("slavePointCut()")
    public void slave(){
        DBDataSourceHolder.slave();
    }

}
