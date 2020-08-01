package ge.exen.configs;

import ge.exen.models.UserPrincipal;
import ge.exen.services.ChatSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.security.Principal;

import static ge.exen.configs.GlobalConstants.DEBUG;
import static ge.exen.configs.SocketConstants.*;
import static org.springframework.messaging.simp.stomp.StompCommand.SEND;
import static org.springframework.messaging.simp.stomp.StompCommand.SUBSCRIBE;

/**
 * Simple class which produces new SecureInterceptors
 */
@Component
public class InterceptorFactory {


    @Autowired
    private ChatSecurityService chatSecurityService;

    /**
     * Channel interceptor which helps with:
     * only allowing /app SEND messages
     * check user privileges for chat topic subscription
     */
    public class SecureInterceptor implements ChannelInterceptor {

        /**
         * @param destination destination path
         * @return -1 if invalid topic
         * CHAT_TOPIC if it starts with CHAT_PREFIX
         */
        protected int getTopicType(@Nullable String destination) {
            if (destination == null) return INVALID_TOPIC;

            if (destination.startsWith(CHAT_REQUEST_PREFIX)) {
                return CHAT_TOPIC;
            }

            if (destination.startsWith(POST_TOPIC_PREFIX)) {
                return POST_TOPIC;
            }

            return INVALID_TOPIC;
        }

        /**
         * @param message STOMP message
         * @param channel channel
         * @return null if request was denied
         *         given message if request was allowed
         */
        @Override
        public Message<?> preSend(Message<?> message, MessageChannel channel) {

            StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

            // we only allow receiving messages though /app
            if (SEND.equals(headerAccessor.getCommand())) {
                String destination = headerAccessor.getDestination();
                if (destination == null) return null;
                if (!destination.startsWith("/app")) return null;
                return message;
            }

            // SUBSCRIBE attempt checks
            if (SUBSCRIBE.equals(headerAccessor.getCommand())) {
                Principal principal = headerAccessor.getUser();
                if(principal==null){
                    if(DEBUG) System.out.println("User is null");
                    return null;
                }
                long user_id = ((UserPrincipal) principal).getId();

                String destination = headerAccessor.getDestination();
                if(destination==null){
                    if(DEBUG) System.out.println("Destionation is null");
                    return null;
                }

                if(DEBUG){
                    System.out.println("Validating for destination:" + destination);
                    System.out.println("user_id: " + user_id);
                }

                int type = getTopicType(destination);
                switch (type) {
                    case INVALID_TOPIC:
                        return null;
                    case CHAT_TOPIC:
                        destination = destination.substring(CHAT_REQUEST_PREFIX.length());
                        long chat_id = Long.parseLong(destination);
                        if (!chatSecurityService.validateUserChatSubscription(user_id, chat_id))
                            return null;
                        break;
                    case POST_TOPIC:
                        break;
                }
                System.out.println("Allowing to connect");
                return message;
            }
            return message;
        }
    }

    /**
     * @return returns new instance of SecureInterceptor
     */
    public SecureInterceptor getNewInterceptor() {
        return new SecureInterceptor();
    }

}
