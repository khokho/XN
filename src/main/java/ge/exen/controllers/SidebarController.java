package ge.exen.controllers;

import ge.exen.DAO.ExamLecturersDAO;
import ge.exen.DAO.UserSQLDAO;
import ge.exen.models.Chat;
import ge.exen.models.Exam;
import ge.exen.models.StudentExam;
import ge.exen.models.User;
import ge.exen.services.ExamService;
import ge.exen.services.IChatService;
import ge.exen.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SidebarController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IChatService chatService;

    @Autowired
    private UserSQLDAO userDAO;

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamLecturersDAO examLecturersDAO;

    public static class SidebarElement {
        String id;
        String name;
        String path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public SidebarElement(String id, String name, String path) {
            this.id = id;
            this.name = name;
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }



    @ResponseBody
    @GetMapping("/getSidebar")
    public List<SidebarElement> getSidebarElements(){
        List<SidebarElement> sidebar = new ArrayList<>();
        User curUser = userService.getCurrentUser();

        if(curUser==null){
            sidebar.add(new SidebarElement("login", "შესვლა", "/login"));
            return sidebar;
        }


        if(curUser.getStatus().equals(User.ADMIN)){
            sidebar.add(new SidebarElement("register", "იუზერის დამატება", "/admin/register"));
            sidebar.add(new SidebarElement("newExam", "გამოცდის დამატება", "/admin/newExam"));
            sidebar.add(new SidebarElement("examList", "გამოცდები", "/admin/list"));
            sidebar.add(new SidebarElement("queues", "რიგები", "/queues"));
        }
        if(curUser.getStatus().equals(User.LECTURER)){
            sidebar.add(new SidebarElement("examList", "გამოცდები", "/lecturer/exams"));
            List<Exam> exams = examService.getLiveExamsForHighStatus();
            System.out.println(exams.size());
            for(Exam exam:exams){
                sidebar.add(new SidebarElement("posts-" + exam.getID(), exam.getFullName() + " პოსტები", "/posts/"+exam.getID()));
            }
            List<Chat> chats = chatService.getMyChats();
            for(Chat chat:chats){
                User user = userDAO.getUser(chat.getstudentId());
                sidebar.add(new SidebarElement("chat-" + chat.getChatId(), "ჩატი: " + user.getName(), "/chat/"+chat.getChatId()));
            }
        }

        if(curUser.getStatus().equals(User.STUDENT)){
            sidebar.add(new SidebarElement("exam", "გამოცდა", "/eh"));
            sidebar.add(new SidebarElement("queues","რიგები", "/queues"));
            StudentExam exam = examService.getLiveExamForCurrentStudent();
            sidebar.add(new SidebarElement("posts-"+exam.getExamId(), "პოსტები","/posts/"+exam.getExamId()));
            List<Long> lectors = examLecturersDAO.getLecturerIds(exam.getExamId());
            List<Chat> chats = chatService.getMyChats();

            for(Chat chat:chats){
                if(!lectors.contains(chat.getlectorId()))continue;
                User user = userDAO.getUser(chat.getlectorId());
                sidebar.add(new SidebarElement("chat-"+chat.getChatId(), "ჩატი: " + user.getName(), "/chat/"+chat.getChatId()));
                lectors.remove(chat.getlectorId());
            }

            for(Long lectorId:lectors){
                User user = userDAO.getUser(lectorId);
                sidebar.add(new SidebarElement("newchat-" + lectorId, "ჩატი: " + user.getName() + " " + user.getLastName(), "/startChat/"+lectorId));
            }
        }
        return sidebar;
    }

}
