package com.boonya.hadoop.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

import java.io.IOException;
import java.net.URI;

@Slf4j
public class HdfsUtils {

    /**
     * 获取文件系统
     *
     * @param hdfsUri nameNode地址 如"hdfs://10.10.1.142:9000"
     * @return
     */
    public static FileSystem getFileSystem(String hdfsUri) {
        //读取配置文件
        Configuration conf = new Configuration();
        // 文件系统
        FileSystem fs = null;
        if (StringUtils.isBlank(hdfsUri)) {
            // 返回默认文件系统  如果在 Hadoop集群下运行，使用此种方法可直接获取默认文件系统
            try {
                fs = FileSystem.get(conf);
            } catch (IOException e) {
                log.error("", e);
            }
        } else {
            // 返回指定的文件系统,如果在本地测试，需要使用此种方法获取文件系统
            try {
                URI uri = new URI(hdfsUri.trim());
                fs = FileSystem.get(uri, conf);
            } catch (Exception e) {
                log.error("", e);
            }
        }
        return fs;
    }

    /**
     * 创建文件目录
     *
     * @param hdfsUri
     * @param path
     */
    public static void mkdir(String hdfsUri, String path) {
        try {
            // 获取文件系统
            FileSystem fs = getFileSystem(hdfsUri);
            if (StringUtils.isNotBlank(hdfsUri)) {
                path = hdfsUri + path;
            }
            // 创建目录
            fs.mkdirs(new Path(path));
            //释放资源
            fs.close();
        } catch (IllegalArgumentException | IOException e) {
            log.error("", e);
        }
    }

    /**
     * 删除文件或者文件目录
     *
     * @param path
     */
    public static void rmdir(String hdfsUri, String path) {
        try {
            // 返回FileSystem对象
            FileSystem fs = getFileSystem(hdfsUri);
            if (StringUtils.isNotBlank(hdfsUri)) {
                path = hdfsUri + path;
            }
            // 删除文件或者文件目录  delete(Path f) 此方法已经弃用
            fs.delete(new Path(path), true);
            // 释放资源
            fs.close();
        } catch (IllegalArgumentException | IOException e) {
            log.error("", e);
        }
    }

    /**
     * 根据filter获取目录下的文件
     *
     * @param path
     * @param pathFilter
     * @return String[]
     */
    public static String[] listFile(String hdfsUri, String path, PathFilter pathFilter) {
        String[] files = new String[0];
        try {
            // 返回FileSystem对象
            FileSystem fs = getFileSystem(hdfsUri);

            if (StringUtils.isNotBlank(hdfsUri)) {
                path = hdfsUri + path;
            }

            FileStatus[] status;
            if (pathFilter != null) {
                // 根据filter列出目录内容
                status = fs.listStatus(new Path(path), pathFilter);
            } else {
                // 列出目录内容
                status = fs.listStatus(new Path(path));
            }
            // 获取目录下的所有文件路径
            Path[] listedPaths = FileUtil.stat2Paths(status);
            // 转换String[]
            if (listedPaths != null && listedPaths.length > 0) {
                files = new String[listedPaths.length];
                for (int i = 0; i < files.length; i++) {
                    files[i] = listedPaths[i].toString();
                }
            }
            // 释放资源
            fs.close();
        } catch (IllegalArgumentException | IOException e) {
            log.error("", e);
        }
        return files;
    }

    /**
     * 文件上传至 HDFS
     *
     * @param hdfsUri
     * @param delSrc    指是否删除源文件，true为删除，默认为false
     * @param overwrite
     * @param srcFile   源文件，上传文件路径
     * @param destPath  hdfs的目的路径
     */
    public static void copyFileToHDFS(String hdfsUri, boolean delSrc, boolean overwrite, String srcFile, String destPath) {
        // 源文件路径是Linux下的路径，如果在 windows 下测试，需要改写为Windows下的路径，比如D://hadoop/djt/weibo.txt
        Path srcPath = new Path(srcFile);

        // 目的路径
        if (StringUtils.isNotBlank(hdfsUri)) {
            destPath = hdfsUri + destPath;
        }
        Path dstPath = new Path(destPath);
        // 实现文件上传
        try {
            // 获取FileSystem对象
            FileSystem fs = getFileSystem(hdfsUri);
            fs.copyFromLocalFile(srcPath, dstPath);
            fs.copyFromLocalFile(delSrc, overwrite, srcPath, dstPath);
            //释放资源
            fs.close();
        } catch (IOException e) {
            log.error("", e);
        }
    }

    /**
     * 从 HDFS 下载文件
     *
     * @param srcFile
     * @param destPath 文件下载后,存放地址
     */
    public static void getFile(String hdfsUri, String srcFile, String destPath) {
        // 源文件路径
        if (StringUtils.isNotBlank(hdfsUri)) {
            srcFile = hdfsUri + srcFile;
        }
        Path srcPath = new Path(srcFile);
        Path dstPath = new Path(destPath);
        try {
            // 获取FileSystem对象
            FileSystem fs = getFileSystem(hdfsUri);
            // 下载hdfs上的文件
            fs.copyToLocalFile(srcPath, dstPath);
            // 释放资源
            fs.close();
        } catch (IOException e) {
            log.error("", e);
        }
    }

    /**
     * 获取 HDFS 集群节点信息
     *
     * @return DatanodeInfo[]
     */
    public static DatanodeInfo[] getHDFSNodes(String hdfsUri) {
        // 获取所有节点
        DatanodeInfo[] dataNodeStats = new DatanodeInfo[0];
        try {
            // 返回FileSystem对象
            FileSystem fs = getFileSystem(hdfsUri);
            // 获取分布式文件系统
            DistributedFileSystem hdfs = (DistributedFileSystem) fs;
            dataNodeStats = hdfs.getDataNodeStats();
        } catch (IOException e) {
            log.error("", e);
        }
        return dataNodeStats;
    }

    /**
     * 查找某个文件在 HDFS集群的位置
     *
     * @param filePath
     * @return BlockLocation[]
     */
    public static BlockLocation[] getFileBlockLocations(String hdfsUri, String filePath) {
        // 文件路径
        if (StringUtils.isNotBlank(hdfsUri)) {
            filePath = hdfsUri + filePath;
        }
        Path path = new Path(filePath);

        // 文件块位置列表
        BlockLocation[] blkLocations = new BlockLocation[0];
        try {
            // 返回FileSystem对象
            FileSystem fs = getFileSystem(hdfsUri);
            // 获取文件目录
            FileStatus filestatus = fs.getFileStatus(path);
            //获取文件块位置列表
            blkLocations = fs.getFileBlockLocations(filestatus, 0, filestatus.getLen());
        } catch (IOException e) {
            log.error("", e);
        }
        return blkLocations;
    }


    /**
     * 判断目录是否存在
     *
     * @param hdfsUri
     * @param filePath
     * @param create
     * @return
     */
    public boolean existDir(String hdfsUri, String filePath, boolean create) {
        boolean flag = false;

        if (StringUtils.isEmpty(filePath)) {
            return flag;
        }
        try {
            Path path = new Path(filePath);
            // FileSystem对象
            FileSystem fs = getFileSystem(hdfsUri);
            if (create) {
                if (!fs.exists(path)) {
                    fs.mkdirs(path);
                }
            }
            if (fs.isDirectory(path)) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("", e);
        }

        return flag;
    }
}
