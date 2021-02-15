## 程序jar包运行
   
利用spark-submit指令运行(在spark安装目录bin下)：
   
       spark-submit \
           --class com.patrol.spark.PatrolSparkServiceApplication  \
           --executor-memory 4G \
           --num-executors 8 \
           --master yarn-client \
       /data/test/sb-spark-0.0.1-SNAPSHOT.jar
       
阅读代码说明：https://blog.csdn.net/boonya/article/details/108367451