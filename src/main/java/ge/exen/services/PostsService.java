package ge.exen.services;

import ge.exen.DAO.ExamLecturersDAO;
import ge.exen.DAO.PostsDao;
import ge.exen.dto.PostEditDTO;
import ge.exen.dto.PostWriteDTO;
import ge.exen.listeners.IPostListener;
import ge.exen.models.ExamLecturers;
import ge.exen.models.Post;
import ge.exen.models.User;
import ge.exen.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostsService implements IPostsService{
    @Autowired
    private PostsDao postsDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ExamLecturersDAO examLecturersDAO;

    @Autowired
    private ExamService examService;


    public Post writeNewPost(final PostWriteDTO postDTO){
        User user = userService.getCurrentUser();
        //check if from_id is a lecturer
        if(!user.getStatus().equals(User.LECTURER)) {
            System.out.println(user.getName() + " NOT A LECTURER: " + user.getStatus());
            return null;
        } else {
            System.out.println("LECTURER");
        }

        //check if given lecturer is assigned to the given exam
        if(!examLecturersDAO.check(new ExamLecturers(postDTO.getExamId(), user.getId()))) {
            System.out.println("NOT ASSIGNED");
            return null;
        } else {
            System.out.println("ASSIGNED");
        }

        final Post post = new Post();
        post.setExamId(postDTO.getExamId());
        post.setFromId(user.getId());
        post.setText(postDTO.getText());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setDate(timestamp);
        postsDao.create(post);

        for (IPostListener listener: postListeners) {
            listener.postReceived(post);
        }
        return post;
    }

    public boolean editPost(PostEditDTO postEditDTO){
        if (checkEditPrivileges(postEditDTO.getPostId())) {
            postsDao.updatePostByID(postEditDTO);
            return postEditDTO.getPostId() != -1;
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

    public List<Post> getPostsByUserId(){
        User user = userService.getCurrentUser();
        if(user.getStatus().equals(User.STUDENT))
            return getPostsByStudentId();
        else if(user.getStatus().equals(User.LECTURER))
            return getPostsByLecturerId();
        else return null;
    }

    private List<Post> getPostsByLecturerId() {
        ExamLecturers exams = examService.getLiveExamForCurrentLecturer();
        return postsDao.getAllByExamId(exams.getExamId()); // lecturer is allowed to be on only one exam at a time
    }

    private List<Post> getPostsByStudentId() {
         // remove comment when getCurrentExam() is written in ExamService
        StudentExam currExam = examService.getLiveExamForCurrentStudent(); //gives the exam curr user is writing
        return postsDao.getAllByExamId(currExam.getExamId());
    }

    private final List<IPostListener> postListeners = new ArrayList<>();

    public void addPostListener(IPostListener postListener){
        postListeners.add(postListener);
    }

    public void removePostListener(IPostListener postListener){
        postListeners.remove(postListener);
    }
}
