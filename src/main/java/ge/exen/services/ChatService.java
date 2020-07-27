package ge.exen.services;

import ge.exen.DAO.*;
import ge.exen.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatService implements IChatService {
    @Autowired
    private IUserService userService;

    @Autowired
    private ChatDAO chatDAO;

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    ChatSecurityService chatSecurityService;

    @Autowired
    IExamService examService;

    @Autowired
    IExamLecturerService examLecturerService;

    @Autowired
    ExamLecturersDAO examLecturersDAO;

    @Override
    public List<Chat> getMyChats() {
        return chatDAO.getUserChats(userService.getCurrentUser().getId());
    }

    @Override
    public List<Message> getMessages(long chatId, int from, int to) {
        User user = userService.getCurrentUser();
        chatSecurityService.validateUserChatSubscription(user.getId(), chatId);
        return messageDAO.getMessagesByRange(chatId, from, to);
    }

    @Override
    public List<Message> searchMessages(long chatId, String pattern) {
        User user = userService.getCurrentUser();
        chatSecurityService.validateUserChatSubscription(user.getId(), chatId);
        return messageDAO.getMessageByChatAndText(chatId, pattern);
    }

    @Override
    public Chat startChat(long lectId) {
        User user = userService.getCurrentUser();

        //check if user starting the chat is a student
        if(!user.getStatus().equals(User.STUDENT)) return null;

        //check if current user has any exams at the moment
        if(examService.getExamForCurrentUser() == null) return null;

        StudentExam studentExam = examService.getExamForCurrentUser();
        ExamLecturers examLecturers = new ExamLecturers(studentExam.getExamId(), lectId);
        long examId = studentExam.getExamId();
        examLecturers.setExamId(examId);
        //check if the lecturer is assigned to the same exam as the student (user)
        if(!examLecturersDAO.check(examLecturers)) return null;

        Chat chat = new Chat();
        chat.setExamId(examId);
        chat.setlectorId(lectId);
        chat.setstudentId(user.getId());
        chatDAO.create(chat);
        return chat;
    }
}
