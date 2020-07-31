package ge.exen.services;

import org.springframework.stereotype.Component;

@Component
public class WCQueueService extends QueueService{
    public static final String TYPE = "wc";

    @Override
    public String getType() {
        return TYPE;
    }

}
