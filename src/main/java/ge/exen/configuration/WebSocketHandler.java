package ge.exen.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@EnableWebSocket
public class WebSocketHandler implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new MessageHandler(), "/messenger");
    }

    class MessageHandler extends TextWebSocketHandler{
        List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            sessions.add(session);
        }

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            TextMessage modifiedMessage = new TextMessage(session.getId() + "#" + message.getPayload());
            for(WebSocketSession ss: sessions){
                if(session.getId() == ss.getId())
                    ss.sendMessage(new TextMessage("1" + message.getPayload()));
                else ss.sendMessage(new TextMessage("0" + message.getPayload()));
            }
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
            for(int i = 0; i < sessions.size(); i ++){
                if(session.getId() == sessions.get(i).getId()) {sessions.remove(i);break;}
            }
        }
    }
}
