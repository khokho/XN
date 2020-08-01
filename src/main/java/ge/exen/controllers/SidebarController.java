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
        String name;
        String path;

        public SidebarElement(String name, String path) {
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
            sidebar.add(new SidebarElement("შესვლა", "/login"));
            return sidebar;
        }


        if(curUser.getStatus().equals(User.ADMIN)){
            sidebar.add(new SidebarElement("იუზერის დამატება", "/admin/register"));
            sidebar.add(new SidebarElement("გამოცდის დამატება", "/admin/newExam"));
            sidebar.add(new SidebarElement("გამოცდები", "/admin/list"));
        }
        if(curUser.getStatus().equals(User.LECTURER)){
            sidebar.add(new SidebarElement("გამოცდები", "/lecturer/exams"));
            List<Exam> exams = examService.getExamsForHighStatus();
            System.out.println(exams.size());
            for(Exam exam:exams){
                sidebar.add(new SidebarElement(exam.getFullName() + " პოსტები", "/posts/"+exam.getID()));
            }
            List<Chat> chats = chatService.getMyChats();
            for(Chat chat:chats){
                User user = userDAO.getUser(chat.getstudentId());
                sidebar.add(new SidebarElement("ჩატი: " + user.getName(), "/chat/"+chat.getChatId()));
            }
        }

        if(curUser.getStatus().equals(User.STUDENT)){
            sidebar.add(new SidebarElement("გამოცდა", "/eh"));
            StudentExam exam = examService.getExamForCurrentUser();
            sidebar.add(new SidebarElement("პოსტები","/posts/"+exam.getExamId()));
            List<Long> users = examLecturersDAO.getLecturerIds(exam.getExamId());
            for(Long userId:users){
                User user = userDAO.getUser(userId);
                sidebar.add(new SidebarElement("ჩატი: " + user.getName() + " " + user.getLastName(), "/startChat/"+userId));
            }
        }





        return sidebar;
    }

}
