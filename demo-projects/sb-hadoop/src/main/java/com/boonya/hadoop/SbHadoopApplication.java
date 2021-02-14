package com.boonya.hadoop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 参考文章:https://www.cnblogs.com/ywjfx/p/11352892.html
 */
@SpringBootApplication
public class SbHadoopApplication {

    /**
     *
     * Failed to locate the winutils binary in the hadoop binary path，这个错误表示没有安装本地的hadoop
     *     下载：https://github.com/srccodes/hadoop-common-2.7.1-bin
     *     将bin目录的文件复制到hadoop的目录中(覆盖原有的文件！)
     *
     * win10添加系统环境变量
     *     HADOOP_HOME ：D:\software\hadoop-dev\hadoop-common-2.7.1-bin-master
     * 　　CLASSPATH ：D:\software\hadoop-dev\hadoop-2.7.7\bin\winutils.exe
     *     在path中将变量添加进去 %HADOOP_HOME%\bin
     *
     * 如遇到拒绝访问请设置相关的hosts
     *
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "D:\\software\\hadoop-dev\\hadoop-2.7.7");
        SpringApplication.run(SbHadoopApplication.class, args);
    }

}
