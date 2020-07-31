package ge.exen.services;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class WCQueueService extends QueueService{
    public static final String TYPE = "wc";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void enqueue(){
        //super.enqueue();
        HashMap<String, Boolean> buttons = (HashMap<String, Boolean>) session.getAttribute(BTNS_ATTR_NAME);
        buttons.put(TYPE, true);
        session.setAttribute(BTNS_ATTR_NAME, buttons);
    }

    @Override
    public void cancelWaiting(){
        //super.cancelWaiting();
        HashMap<String, Boolean> buttons = (HashMap<String, Boolean>) session.getAttribute(BTNS_ATTR_NAME);
        buttons.put(TYPE, false);
        session.setAttribute(BTNS_ATTR_NAME, buttons);
    }

}
