package ge.exen.DAO;

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
    Post getPost(int postId);

    /**
     * @param examId
     * @return list of posts with examId
     */
    List<Post> getAllByExamId(int examId);

    /**
     * @param fromId
     * @return list of posts written by user with fromId as ID
     */
    List<Post> getAllByPoster(int fromId);

}
