package com.dataservice.websocket.dpwebsocket.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 服务器工具类
 */
public class ServerUtil {

    private final static Logger logger = LoggerFactory.getLogger(ServerUtil.class);

    /**
     * 获取服务器IP地址
     * @return
     */
    public static String getServerIp(){
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(),e);
        }
        return  localHost.getHostAddress();
    }
}
