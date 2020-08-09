package ge.exen.listeners;

import ge.exen.services.BlankPaperQueueService;
import ge.exen.services.CallExamerQueueService;
import ge.exen.services.WCQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueListener implements IQueueListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public void setPaper(BlankPaperQueueService queue) {
        queue.addListener(this);
    }

    @Autowired
    public void set(CallExamerQueueService queue) {
        queue.addListener(this);
    }

    @Autowired
    public void set(WCQueueService queue) {
        queue.addListener(this);
    }

    @Override
    public void fireQueueUpdate(String name) {
        System.out.println("hi dudes I am websocket other end");
        System.out.println("/topic/queue-"+name);
        messagingTemplate.convertAndSend("/topic/queue-"+name, "g");
    }
}
