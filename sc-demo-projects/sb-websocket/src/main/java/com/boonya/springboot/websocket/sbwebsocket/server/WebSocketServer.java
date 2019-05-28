package com.boonya.springboot.websocket.sbwebsocket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{uuid}")
@Component
public class WebSocketServer {

    private final static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String ,WebSocketServer> webSocketSet = new ConcurrentHashMap<String,WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收uuid
    private String uuid="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("uuid") String uuid) {
        this.session = session;
        webSocketSet.put(uuid,this);     //加入set中
        addOnlineCount();           //在线数加1
        logger.info("有新窗口开始监听:"+uuid+",当前在线人数为" + getOnlineCount());
        this.uuid=uuid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            logger.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this.uuid);  //从set中删除
        subOnlineCount();           //在线数减1
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到来自窗口"+uuid+"的信息:"+message);
        try{
            // 收到消息指令应该对应做处理，然后发送响应消息给客户端
            broadcastOne("服务端已收到页面发送的消息："+message,this.uuid);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
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
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    /**
     * 单播
     * */
    public static void broadcastOne(String message,@PathParam("uuid") String uuid) throws IOException {
        logger.info("推送消息到窗口"+uuid+"，推送内容:"+message);
        for (WebSocketServer item : webSocketSet.values()) {
            try {
                if(item.uuid.equals(uuid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    /**
     * 广播
     * */
    public static void broadcastAll(String message) throws IOException {
        logger.info("推送消息到所有打开窗口，推送内容:"+message);
        //群发消息
        for (WebSocketServer item : webSocketSet.values()) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
