package ge.exen.services;
import ge.exen.DAO.ExamLecturersDAO;
import ge.exen.DAO.PostsDao;
import ge.exen.DAO.UserDAO;
import ge.exen.dto.PostEditDTO;
import ge.exen.dto.PostWriteDTO;
import ge.exen.models.ExamLecturers;
import ge.exen.models.Post;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PipedOutputStream;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

@Service
public class PostsService implements IPostsService{
    @Autowired
    private PostsDao postsDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ExamLecturersDAO examLecturersDAO;

    public Post writeNewPost(final PostWriteDTO postDTO){
        User user = userService.getCurrentUser();
        //check if from_id is a lecturer
        if(!user.getStatus().equals(User.LECTURER)) return null;

        //check if given lecturer is assigned to the given exam
        if(!examLecturersDAO.check(new ExamLecturers(postDTO.getExamId(), user.getId()))) return null;

        final Post post = new Post();
        post.setExamId(postDTO.getExamId());
        post.setFromId(user.getId());
        post.setText(postDTO.getText());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setDate(timestamp);
        postsDao.create(post);
        return post;
    }

    public boolean editPost(PostEditDTO postEditDTO){
        if (checkEditPrivileges(postEditDTO.getPostID())) {
            postsDao.updatePostByID(postEditDTO);
            return postEditDTO.getPostID() != -1;
        }
        return false;
    }

    public boolean removePost(Long postId){
        if (checkEditPrivileges(postId)) {
            return postsDao.removePostByID(postId);
        }
        return false;
    }

    private boolean checkEditPrivileges(Long postId){
        User user = userService.getCurrentUser();
        Post oldPost = postsDao.getPost(postId);

        //check if post with given id exists
        if(oldPost == null) return false;

        //check if the current user is the author of the post (only the author has the privilege to remove post)
        if(oldPost.getFromId() != user.getId()) return false;

        return true;
    }

}
