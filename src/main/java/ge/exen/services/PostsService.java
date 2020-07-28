package ge.exen.services;
import ge.exen.DAO.ExamLecturersDAO;
import ge.exen.DAO.PostsDao;
import ge.exen.DAO.UserDAO;
import ge.exen.dto.writePostDTO;
import ge.exen.models.ExamLecturers;
import ge.exen.models.Post;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Service
public class PostsService {
    @Autowired
    private PostsDao postsDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private HttpSession HttpSession;

    @Autowired
    private ExamLecturersDAO examLecturersDAO;

    public Post writeNewPost(final writePostDTO postDTO){
        //TODO check if from_id is a lecturer
        //TODO check if exam_id is valid
        //TODO check if lecturer is assigned to the given exam
        User user = (User)HttpSession.getAttribute("user");
        //check if from_id is a lecturer
        if(userDao.getStatusByUserId(user.getId()).equals(User.LECTURER)) {
            //check if given lecturer is assigned to the given exam
            if(examLecturersDAO.check(new ExamLecturers(postDTO.getExamId(), user.getId()))) {
                final Post post = new Post();
                post.setExamId(postDTO.getExamId());
                post.setFromId(user.getId());
                post.setText(postDTO.getText());
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                post.setDate(timestamp);
                return post;
            }
        }
        return null;
    }

}
