package ge.exen.services;

import ge.exen.DAO.ChatDAO;
import ge.exen.DAO.ExamLecturersDAO;
import ge.exen.DAO.MessageDAO;
import ge.exen.dto.SendMessageDTO;
import ge.exen.listeners.IChatListener;
import ge.exen.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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


    private final List<IChatListener> chatListeners = new ArrayList<>();

    public void registerChatListener(IChatListener chatListener){
        chatListeners.add(chatListener);
    }

    public void removeChatListener(IChatListener chatListener){
        chatListeners.remove(chatListener);
    }




    @Override
    public List<Chat> getMyChats() {
        return chatDAO.getUserChats(userService.getCurrentUser().getId());
    }

    @Override
    public Long amIChattingWith(long userId){
        for(Chat chat:getMyChats()){
            if(chat.getstudentId()==userId||chat.getlectorId()==userId){
                return chat.getChatId();
            }
        }
        return null;
    }

    @Override
    public List<Message> getMessages(long chatId, int from, int to) {
        User user = userService.getCurrentUser();
        if(!chatSecurityService.validateUserChatSubscription(user.getId(), chatId)) return null;
        return messageDAO.getMessagesByRange(chatId, from, to);
    }

    @Override
    public List<Message> searchMessages(long chatId, String pattern) {
        User user = userService.getCurrentUser();
        if(!chatSecurityService.validateUserChatSubscription(user.getId(), chatId)) return null;
        return messageDAO.getMessagesWithText(chatId, pattern);
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

    @Override
    public boolean sendMessage(SendMessageDTO sendMessageDTO, long fromId){
        //check if the user is assigned to the given chat
        if(!chatSecurityService.validateUserChatSubscription(fromId, sendMessageDTO.getChatId())) return false;

        Message message = new Message();
        message.setFrom(fromId);
        message.setChatId(sendMessageDTO.getChatId());
        message.setSentDate(new Timestamp(System.currentTimeMillis()));
        message.setText(sendMessageDTO.getText());
        message.setType(sendMessageDTO.getType());
        System.out.println(message);
        if(!messageDAO.create(message))return false;
        for(IChatListener listener:chatListeners){
            listener.messageReceived(message);
        }
        return true;
    }
}
