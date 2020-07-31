package ge.exen.services;

import org.springframework.stereotype.Component;

@Component
public class BlankPaperQueueService extends QueueService{
    public static final String TYPE = "blank-paper";

    @Override
    public String getType() {
        return TYPE;
    }
}
