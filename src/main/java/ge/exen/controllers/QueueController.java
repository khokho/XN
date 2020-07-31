package ge.exen.controllers;

import ge.exen.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

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


    @GetMapping(value = "/queues-admin")
    public String getQueuesAdmin(HttpSession session){
        session.setAttribute("content", "queues_admin.jsp");
        session.setAttribute("sidebar", "adminSidebar.jsp");
        session.setAttribute("title", "რიგები");
        return "/template";
    }

    @GetMapping(value = "/queues-student")
    public String getQueuesStudent(HttpSession session){
        session.setAttribute("content", "queues_student.jsp");
        session.setAttribute("sidebar", "student_sidebar.jsp");
        session.setAttribute("title", "რიგები");
        return "/template";
    }


    @ResponseBody
    @RequestMapping(value = "/enqueue/{queueName}")
    public String enqueueStudent(HttpServletRequest req, @PathVariable String queueName){
        //getQueueByType(queueName).enqueue();
        System.out.println("vitom daemata");
        return "{res:yes}";
    }

    @ResponseBody
    @RequestMapping(value = "/cancel-waiting/{queueName}")
    public String cancelWaiting(HttpServletRequest req, @PathVariable String queueName){
        //getQueueByType(queueName).cancelWaiting();
        System.out.println("vitom daacancela");
        return "{res:yes}";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/dequeue/{queueName}")
    public String dequeue(@PathVariable String queueName){
        //getQueueByType(queueName).dequeue();
        System.out.println("vitom amoigo");
        return "{res:yes}";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/clear-queue/{queueName}")
    public String clearQueue(@PathVariable String queueName){
        //getQueueByType(queueName).clearQueue();
        System.out.println("vitom gaasuptava");
        return "{res:yes}";
    }
    @ResponseBody
    @RequestMapping(value = "/get-disabled/{queueName}")
    public Boolean getDisabledState(@PathVariable String queueName){
        //Boolean status = getQueueByType(queueName).getDisabledState(queueName);
        System.out.println("state aris");
        return false;
    }
    
    @ResponseBody
    @RequestMapping(value = "/get-anticipants/{queueName}}")
    public Integer getAnticipants(@PathVariable String queueName){
        //Integer cnt = getQueueByType(queueName).getAnticipants(queueName);
        //return cnt;
        System.out.println("raodenoba aris");
        return 34;
    }

    @ResponseBody
    @GetMapping("/getqueuesize")
    public Integer getVal(){
        return 100;
    }

    @ResponseBody
    @GetMapping("/getdisabled")
    public Boolean disabled(){
        return false;
    }

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
