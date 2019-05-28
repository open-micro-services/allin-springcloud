package com.dataservice.websocket.dpwebsocket.websocket;

import com.dataservice.websocket.dpwebsocket.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WS服务端
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
        this.session = session;
        this.uuid = uuid;
        // 记录连接客户端标识
        webSocketSet.put(uuid, this);
        // 增加在线数量(递增)
        WebSocketServer.incrementOnlineCount();
        logger.info("有新窗口开始监听:" + uuid + ",当前在线人数为" + WebSocketServer.getOnlineCount());
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", UUID.randomUUID().toString());
            map.put("message", "连接成功消息");
            String json = JSONUtil.getJsonFromObject(new WebSocketMessage(1, "连接成功", map));
            sendMessage(json);
        } catch (IOException e) {
            logger.error("websocket IO异常");
        }
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
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到来自窗口" + uuid + "的信息:" + message);
        try {
            // 收到消息指令应该对应做处理，然后发送响应消息给客户端
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", UUID.randomUUID().toString());
            map.put("message", message);
            String json = JSONUtil.getJsonFromObject(new WebSocketMessage(1, "接收消息成功", map));
            WebSocketServer.broadcastOne(json, this.uuid);
        } catch (Exception e) {
            e.printStackTrace();
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
     * 获取在线数量
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount.get();
    }

    /**
     * 递增在线数量
     */
    public static synchronized void incrementOnlineCount() {
        WebSocketServer.onlineCount.incrementAndGet();
    }

    /**
     * 递减在线数量
     */
    public static synchronized void decrementOnlineCount() {
        WebSocketServer.onlineCount.decrementAndGet();
    }

}
