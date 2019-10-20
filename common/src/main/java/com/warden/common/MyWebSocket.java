package com.warden.common;


import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * @author YangJiaYing
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
    private Logger logger = Logger.getLogger(getClass().getName());
    private static int onlineCount = 0;
    private static CopyOnWriteArrayList<MyWebSocket> webSockets = new CopyOnWriteArrayList<>();
    private Session session;

    /**
     * 连接成功调用的方法
     *
     * @param session 与客户端连接的会话
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        addOnlineCount();
        logger.info("有新连接加入！ 当前在线人数为：" + getOnlineCount());
        logger.info("session:"+session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSockets.remove(this); //从set中删除
        subOnlineCount();           //在线人数减1
        logger.info("有一个连接关闭！ 当前在线人数：" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 发送给客户端的消息
     * @param session 与客户端的会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息：" + message);

        //群发消息
        for (MyWebSocket user :
                webSockets) {
            try {
                user.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * @param message 群发的内容
     */
    public static void sendInfo(String message) {
        for (MyWebSocket user :
                webSockets) {
            try {
                user.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendInfo(String message,@NotNull Session session) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
