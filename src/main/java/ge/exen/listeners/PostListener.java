package ge.exen.listeners;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.UserDAO;
import ge.exen.models.Exam;
import ge.exen.models.Post;
import ge.exen.models.PostJSON;
import ge.exen.models.User;
import ge.exen.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostListener implements IPostListener{

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private ExamDao examDao;

    @Autowired
    public void setPostsService(PostsService postsService) {
        postsService.addPostListener(this);
    }

    @Override
    public void postReceived(Post post){
        PostJSON postJSON = new PostJSON();
        postJSON.setText(post.getText());
        User lecturer = userDAO.getUser(post.getFromId());
        postJSON.setLecturer(lecturer.getName() + " " + lecturer.getLastName());
        Exam exam = examDao.get(post.getExamId());
        postJSON.setExam(exam.getFullName());
        postJSON.setDate(post.getDate());
        postJSON.setPostId(post.getPostId());
        messagingTemplate.convertAndSend("/topic/posts-"+post.getExamId(), postJSON);
    }


}
