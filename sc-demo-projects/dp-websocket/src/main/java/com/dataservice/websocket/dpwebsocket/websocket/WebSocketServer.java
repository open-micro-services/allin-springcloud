package com.dataservice.websocket.dpwebsocket.websocket;

import com.dataservice.websocket.dpwebsocket.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * WebSocket服务端
 */
@ServerEndpoint("/websocket/{uuid}")
@Component
public class WebSocketServer {

    private final static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    // 静态变量，用来记录当前在线连接数,应该把它设计成线程安全的，原子变量操作时最合适
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    //concurrent包的线程安全ConcurrentHashMap，用来存放每个客户端对应的WebSocketServer对象
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<String, WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //与某个客户端连接标识uuid
    private String uuid = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uuid") String uuid) {
        // socket连接回话
        this.session = session;
        // 设置客户端标识
        this.uuid = uuid;
        // 记录连接客户端标识
        webSocketSet.put(uuid, this);
        // 增加在线数量(递增)
        WebSocketServer.incrementOnlineCount();
        // 输出INFO日志
        logger.info("有新窗口开始监听:" + uuid + ",当前在线人数为" + WebSocketServer.getOnlineCount());
        // 反馈连接状态
        sendFeedbackMessage(1,"连接成功",uuid);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从内存对象中移除连接标识
        webSocketSet.remove(this.uuid);
        // 递减连接数量
        WebSocketServer.decrementOnlineCount();
        // 输出INFO日志在线数量
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        boolean success=false;
        logger.info("收到来自窗口" + uuid + "的信息:" + message);
        // 解析页面主动发送参数
        Map<String,Object> param=null;
        try {
            param=JSONUtil.getObjectFromJson(message,Map.class);
        } catch (IOException e) {
            sendFeedbackMessage(0,"指令参数解析错误",uuid);
            logger.error("参数格式错误:"+e.getMessage());
            e.printStackTrace();
            return ;
        }

        try {
            //解析参数服务类型
            String service= (String) param.get("service");
            if(WebSocketEnum.STATISTICS_USER.getValue().equals(service)){
                // 做相应的设备用户统计数据处理

                success=true;
            }else  if(WebSocketEnum.STATISTICS_EVENTS.getValue().equals(service)){
                // 做相应的事件周期及列表统计数据处理

                success=true;
            }
        } catch (Exception e) {
            logger.error("参数解析成功，但发生了如下错误:"+e.getMessage());
            e.printStackTrace();
        }finally {
            if(success){
                sendFeedbackMessage(1,"指令发送成功",uuid);
            }else{
                sendFeedbackMessage(0,"指令发送失败，请核对参数是否正确",uuid);
            }
        }
    }

    /**
     * 服务端产生错误调用方法
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送消息
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 单播消息
     */
    public static void broadcastOne(String message, @PathParam("uuid") String uuid) throws IOException {
        logger.info("推送消息到窗口" + uuid + "，推送内容:" + message);
        for (WebSocketServer item : webSocketSet.values()) {
            try {
                if (item.uuid.equals(uuid)) {
                    item.sendMessage(message);
                    logger.info("UUID:" + item.uuid + "....已推送!");
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    /**
     * 广播消息
     */
    public static void broadcastAll(String message) throws IOException {
        logger.info("推送消息到所有打开窗口，推送内容:" + message);
        //群发消息
        for (WebSocketServer item : webSocketSet.values()) {
            try {
                item.sendMessage(message);
                logger.info("UUID:" + item.uuid + "....已推送!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送反馈指令消息
     *
     * @param status
     * @param message
     * @param uuid
     */
    public static void sendFeedbackMessage(int status,String message,String uuid){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String json = JSONUtil.getJsonFromObject(new WebSocketMessage(status, message, map));
            // 响应客户端请求页面
            WebSocketServer.broadcastOne(json, uuid);
        } catch (Exception e) {
            logger.error("UUID:" + uuid + ".....给客户端的反馈消息发送异常!");
            e.printStackTrace();
        }
    }

    /**
     * 封装反馈指令消息
     *
     * @param status
     * @param message
     * @param obj
     */
    public static String getFeedbackMessage(int status,String message,Object obj){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            return  JSONUtil.getJsonFromObject(new WebSocketMessage(status, message, obj));
        } catch (Exception e) {
            logger.error("对象转JSON异常!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取在线数量
     *
     * @return
     */
    public static  int getOnlineCount() {
        return onlineCount.get();
    }

    /**
     * 递增在线数量
     */
    public static  void incrementOnlineCount() {
        WebSocketServer.onlineCount.incrementAndGet();
    }

    /**
     * 递减在线数量
     */
    public static  void decrementOnlineCount() {
        WebSocketServer.onlineCount.decrementAndGet();
    }

    /**
     * 获取连接客户端数据
     *
     * @return
     */
    public static List<String> getClients(){
        List<String> list=new ArrayList<String>();
        // 并发避免遍历出问题
        Enumeration<String> keys=webSocketSet.keys();
        while (keys.hasMoreElements()){
            list.add(keys.nextElement());
        }
        return list;
    }

}
