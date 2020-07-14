package ge.exen.DAO;

import ge.exen.dto.PostEditDTO;
import ge.exen.models.Post;

import java.util.List;

public interface PostsDao {

    /**
     * @param post add post in db
     */
    void create(Post post);

    /**
     * @param postId
     * @return post with postId
     */
    Post getPost(Long postId);

    /**
     * @param examId
     * @return list of posts with examId
     */
    List<Post> getAllByExamId(Long examId);

    /**
     * @param fromId
     * @return list of posts written by user with fromId as ID
     */
    List<Post> getAllByPoster(Long fromId);

    /**
     * @param postEditDTO
     * updates text in the post with given postID
     */
    void updatePostByID(PostEditDTO postEditDTO);

    /**
     * @param postId
     * removes post with the given postID
     * @return true if remove is successful, otherwise false
     */
    boolean removePostByID(Long postId);


}
