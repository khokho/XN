package ge.exen.services;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CallExamerQueueService extends QueueService{
    public static final String TYPE = "call-examer";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void enqueue(){
        //super.enqueue();
        HashMap<String, Boolean> buttons = (HashMap<String, Boolean>) session.getAttribute(BTNS_ATTR_NAME);
        buttons.put(TYPE, true);
    }

    @Override
    public void cancelWaiting(){
        //super.cancelWaiting();
        HashMap<String, Boolean> buttons = (HashMap<String, Boolean>) session.getAttribute(BTNS_ATTR_NAME);
        buttons.put(TYPE, false);
    }
}
