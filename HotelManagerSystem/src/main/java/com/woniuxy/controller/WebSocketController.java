package com.woniuxy.controller;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;


import com.woniuxy.util.WebSocketUtil;


@ServerEndpoint("/WebSocketController/{userName}") // 表示接受的是STOMP协议提
@Component
// 交的数据
public class WebSocketController {
	// 建立连接
	@OnOpen
	public void openSession(@PathParam("userName") String userName, Session session) {
		// 消息
		String message = "欢迎:" + userName + "加入群聊";
		// 加入聊天室
		WebSocketUtil.MESSAGEMAP.put(userName, session);
		// 发送消息
		WebSocketUtil.sendMessageToAll(message);
	}

	@OnMessage
	public void onMessage(@PathParam("userName") String userName, String message) {
		message = userName + ":" + message;
		WebSocketUtil.sendMessageToAll(message);
	}

	// 离开聊天室
	@OnClose
	public void onClose(@PathParam("userName") String userName, Session session) {
		// 将当前用户从map中移除 注销
		WebSocketUtil.MESSAGEMAP.remove(userName);
		String message = "用户:" + userName + "离开聊天室";		
		// 群发消息
		WebSocketUtil.sendMessageToAll(message);
		// 关闭session
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 连接异常
	@OnError
	public void onError(Session session, Throwable throwable) {
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
