package ge.exen.controllers;

import ge.exen.models.User;
import ge.exen.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QueueController {

    @Autowired
    UserService userService;

    @Autowired
    BlankPaperQueueService blankPaperQueue;

    @Autowired
    CallExamerQueueService callExamerQueue;

    @Autowired
    WCQueueService wcQueue;


    @GetMapping(value = "/queues")
    public String getQueues(Model model) {
        User user = userService.getCurrentUser();
        if (user.getStatus().equals(User.STUDENT))
            return getQueuesStudent(model);
        else if (user.getStatus().equals(User.ADMIN))
            return getQueuesAdmin(model);
        else
            return "insuffPriv";
    }

    @GetMapping(value = "/queues-admin")
    public String getQueuesAdmin(Model model) {
        model.addAttribute("content", "queues_admin.jsp");
        model.addAttribute("title", "რიგები");
        return "/template";
    }

    @GetMapping(value = "/queues-student")
    public String getQueuesStudent(Model model) {
        model.addAttribute("content", "queues_student.jsp");
        model.addAttribute("title", "რიგები");
        return "/template";
    }


    @ResponseBody
    @RequestMapping(value = "/enqueue/{queueName}")
    public String enqueueStudent(HttpServletRequest req, @PathVariable String queueName) {
        if (getQueueByType(queueName) != null) {
            System.out.println(getQueueByType(queueName));
            getQueueByType(queueName).enqueue();
            return "{res:yes}";
        }
        return "{res:no}";
    }

    @ResponseBody
    @RequestMapping(value = "/cancel-waiting/{queueName}")
    public String cancelWaiting(HttpServletRequest req, @PathVariable String queueName) {
        getQueueByType(queueName).cancelWaiting();
        return "{res:yes}";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/dequeue/{queueName}")
    public String dequeue(@PathVariable String queueName) {
        getQueueByType(queueName).dequeue();
        return "{res:yes}";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/clear-queue/{queueName}")
    public String clearQueue(@PathVariable String queueName) {
        getQueueByType(queueName).clearQueue();
        return "{res:yes}";
    }

    @ResponseBody
    @RequestMapping(value = "/get-disabled/{queueName}")
    public Boolean getDisabledState(@PathVariable String queueName) {
        Boolean status = getQueueByType(queueName).getDisabledState();
        return status;
    }

    @ResponseBody
    @RequestMapping(value = "/get-anticipants/{queueName}")
    public Integer getAnticipants(@PathVariable String queueName) {
        Integer cnt = getQueueByType(queueName).getAnticipants();
        return cnt;
    }

    @ResponseBody
    @GetMapping("/getqueuesize")
    public Integer getVal() {
        return 100;
    }

    @ResponseBody
    @GetMapping("/getdisabled")
    public Boolean disabled() {
        return false;
    }


    /**
     * returns queueservice of the given queue type
     *
     * @param queueType String representing the queue
     * @return QueueService
     */
    private QueueService getQueueByType(String queueType) {
        switch (queueType) {
            case BlankPaperQueueService.TYPE:
                return blankPaperQueue;
            case CallExamerQueueService.TYPE:
                return callExamerQueue;
            case WCQueueService.TYPE:
                return wcQueue;
        }
        return null;
    }


}
