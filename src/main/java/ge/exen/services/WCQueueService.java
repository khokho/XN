package ge.exen.services;

import ge.exen.models.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class WCQueueService extends QueueService {
    public static final String TYPE = "wc";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void enqueue() {
        //super.enqueue();
        changeDisabledState(TYPE);
    }

    @Override
    public void cancelWaiting() {
        //super.cancelWaiting();
        changeDisabledState(TYPE);
    }

    @Override
    public User dequeue() {
        User current = super.dequeue();
        if (checkQueueStatus()) {
            changeDisabledState(TYPE);
        }
        return current;
    }

    @Override
    public void clearQueue() {
        //super.clearQueue();
        if (getDisabledState(TYPE)) {
            changeDisabledState(TYPE);
        }
    }

}
