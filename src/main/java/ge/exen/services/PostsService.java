package ge.exen.services;

import ge.exen.DAO.ExamLecturersDAO;
import ge.exen.DAO.PostsDao;
import ge.exen.dto.PostEditDTO;
import ge.exen.dto.PostWriteDTO;
import ge.exen.listeners.IPostListener;
import ge.exen.models.ExamLecturers;
import ge.exen.models.Post;
import ge.exen.models.StudentExam;
import ge.exen.models.User;
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
        boolean edited = false;
        if (checkEditPrivileges(postEditDTO.getPostId())) {
            postsDao.updatePostByID(postEditDTO);
            edited = postEditDTO.getPostId() != -1;
        }

        for (IPostListener listener: postListeners) {
            listener.postEdited(postEditDTO, 3);//FIXME: what is examID
        }
        return edited;
    }

    public boolean removePost(Long postId, Long examId){
        boolean removed = false;
        if (checkEditPrivileges(postId)) {
            removed = postsDao.removePostByID(postId);

            for (IPostListener listener: postListeners) {
                listener.postRemoved(postId, examId);
            }
        }
        return removed;
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

    public List<Post> getPostsByExamId(long examId){
        User user = userService.getCurrentUser();
        if(user.getStatus().equals(User.STUDENT))
            return getPostsByStudentId(examId);
        else if(user.getStatus().equals(User.LECTURER))
            return getPostsByLecturerId(examId);
        else return null;
    }

    private List<Post> getPostsByLecturerId(Long examId) {
        //ExamLecturers exams = examService.getLiveExamForCurrentLecturer();
        User user = userService.getCurrentUser();
        List<ExamLecturers> exams = examService.getLiveExamForCurrentLecturer();
        for (int i = 0; i < exams.size(); i++) {
            if(exams.get(i).getExamId() == examId) return postsDao.getAllByExamId(examId);
        }
        return null;
    }

    private List<Post> getPostsByStudentId(Long examId) {
         // remove comment when getCurrentExam() is written in ExamService
        StudentExam currExam = examService.getLiveExamForCurrentStudent(); //gives the exam curr user is writing
        if(examId != currExam.getExamId()) return null;
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
