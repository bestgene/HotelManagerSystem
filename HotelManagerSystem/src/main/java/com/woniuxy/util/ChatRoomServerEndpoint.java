package com.woniuxy.util;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * 聊天室服务端
 * 标注为端点：@ServerEndpoint，其中"/chat-room/{username}"为访问路径
 */
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {
    /**
     * 存储所有存活的用户
     * 注意1：高并发问题
     * 注意2：livingSessions必须是全局变量（保证全局就他一个变量，否则每次调用都会被刷新）
     * 注意3：很难保证，用户在退出聊天室时，正确调用了WebSocket.close()，也就是调用后端的onClose()方法，这样
     *        就可能会导致Session无法被有效清除，livingSessions会越来越大，服务器压力也会越来越大。
     *        所以，我们需要周期性的去检查用户是否还处于活跃状态，不活跃的，移除该用户的session
     */
    private static Map<String , Session> livingSessions = new ConcurrentHashMap<>();

    /**
     * 前端一旦启用WebSocket,机会调用@OnOpen注解标注的方法
     * @param username 路径参数
     * @param session 会话，每个访问对象都会有一个单独的会话
     */
    @OnOpen
    public void openSession(@PathParam("username") String username, Session session){
        livingSessions.put(session.getId(), session);
        sendTextAll("欢迎用户【" + username +"】来到聊天室！");
    }

    /**
     * 服务端发送消息给前端时调用
     * @param username   路径参数
     * @param session    会话，每个访问对象都会有一个单独的会话
     * @param message    待发送的消息
     */
    @OnMessage
    public void onMessage(@PathParam("username") String username, Session session, String message){
        sendTextAll("用户【" + username + "】：" + message);
    }

    /**
     * 客户端关闭WebSocket连接时，调用标注@OnClose的方法
     * @param username   路径参数
     * @param session    会话，每个访问对象都会有一个单独的会话
     */
    @OnClose
    public void onClose(@PathParam("username") String username, Session session){
        //将当前用户移除
        livingSessions.remove(session.getId());
        //给所有存活的用户发送消息
        sendTextAll("用户【" + username +"】离开聊天室！");
    }

    /**
     * 向指定Session(用户)发送message
     */
    private void sendText(Session session, String message){
        //发送消息对象
        RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
        try {
            //发送消息
            basicRemote.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历所有存活的用户，并发送消息（PS：就是广播消息）
     */
    private void sendTextAll(String message){
        //lambda表达式
        livingSessions.forEach((sessionId, session) -> sendText(session, message));
    }
}
