package xyz.likailing.cloud.service.msg.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author 12042
 * @create 2023/3/20 18:29
 */
@Slf4j
@Service
@ServerEndpoint("/api/msg/{username}")
public class WebSocketServer {
    //记录在线连接数
    private static int onlineCount = 0;
    //线程安全set，存放每个客户端对于的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers = new CopyOnWriteArraySet<>();
    //连接会话
    private Session session;
    //接收uid
    private String username = "";
    /**
     * 连接建立成功
     * 
 * @param session
 * @param username
     * @return void
     * @author likailing
     * @create 2023/3/20
     **/
    
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        this.session = session;
        webSocketServers.add(this);
        this.username = username;
        addOnlineCount();
        try{
            sendMessage("connect_success");
            log.info("有新窗口开始监听："+username+",当前在线人数:"+getOnlineCount());
        }catch (IOException e){
            log.error("Websocket IO Exception");
            e.printStackTrace();
        }

    }
    /**
     * 连接关闭
     * 
     * @return void
     * @author likailing
     * @create 2023/3/20
     **/
    
    @OnClose
    public void onClose(){
        webSocketServers.remove(this);
        subOnlineCount();
        log.info("释放的连接为:"+username);
        log.info("有1连接关闭，当前在线人数为"+getOnlineCount());
    }
    /**
     * 收到客户端消息后
     * 
 * @param messgae
 * @param session
     * @return void
     * @author likailing
     * @create 2023/3/20
     **/
    
    @OnMessage
    public void onMessage(String messgae,Session session){
        log.info("收到来自窗口"+username+"的信息:"+messgae);
        //群发消息
        for (WebSocketServer webSocketServer : webSocketServers) {
            try{
                webSocketServer.sendMessage(messgae);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    @OnError
    public void onError(Session session, Throwable error){
        log.error("webSocket出现错误");
        error.printStackTrace();
    }

    /**
     * 主动推送消息
     * 
 * @param message
     * @return void
     * @author likailing
     * @create 2023/3/20
     **/
    
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    /**
     * 群发自定义消息
     * 
     * @param messgae
     * @param username
     * @return void
     * @author likailing
     * @create 2023/3/20
     **/
    
    public static void sendInfo(String messgae,@PathParam("username") String username) throws IOException{
        log.info("推送消息到窗口："+username+",推送内容:"+messgae);
        for (WebSocketServer webSocketServer : webSocketServers) {
            try{
                //如果username为空则群发，否则发给指定用户
                if(username==null){
                    webSocketServer.sendMessage(messgae);
                } else if (webSocketServer.username.equals(username)) {
                    webSocketServer.sendMessage(messgae);
                }
            }catch (IOException e){
                e.printStackTrace();
                continue;
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

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketServers;
    }

}
