package ge.exen.configs;

import ge.exen.models.User;
import ge.exen.models.UserPrincipal;
import ge.exen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

import static ge.exen.configs.SocketConstants.SOCKET_ENDPOINT;

@Configuration
@EnableWebSocketMessageBroker
public class BrokerConfig implements WebSocketMessageBrokerConfigurer  {

    @Autowired
    UserService userService;

    /**
     * This class passes user_id as a Principal
     * so we can securely recognize users in websocket requests.
     * if there is no user logged in then socket won't open
     */
    private class HadshakeWithUser extends DefaultHandshakeHandler {
        @Override
        protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
            User user = userService.getCurrentUser();
            if(user==null)return null;
            return new UserPrincipal(user.getId());
        }
    }

    @Autowired
    InterceptorFactory interceptorFactory;

    /**
     * @param registration puts interceptor inbetween any requests going to socket
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(interceptorFactory.getNewInterceptor());
    }

    /**
     * @param registry sets up default socket endpoint
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(SOCKET_ENDPOINT).setHandshakeHandler(new HadshakeWithUser()).withSockJS();
    }

    /**
     * @param registry sets up default prefixes
     *  /app/* requests go to controllers
     *  /topic/* requests go to broker
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/topic");
    }

}
