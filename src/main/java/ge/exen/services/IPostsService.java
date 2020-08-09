package ge.exen.services;

import ge.exen.dto.PostEditDTO;
import ge.exen.dto.PostWriteDTO;
import ge.exen.models.Post;

import java.util.List;

public interface IPostsService {
    /**
     * adds created post in database
     * @param postDTO input for creating a post
     * @return created post
     */
    Post writeNewPost(final PostWriteDTO postDTO);

    /**
     * updates post with given postId
     * @param postEditDTO input for editing the post (PostId and text)
     * @return true if successfully edited, false otherwise
     */
    boolean editPost(PostEditDTO postEditDTO);

    /**
     * removes post with given postId from the database
     * @param postId
     * @return true if successfully edited, false otherwise
     */
    boolean removePost(Long postId, Long examId);

    /**
     * If logged in user is a lecturer, they get their posts
     * If logged in user is a student, they are writing an exam, let's call it current exam.
     * So they get posts written in current exam.
     * @return list of Posts accessible for current user
     */
    List<Post> getPostsByExamId(long examId);


}
