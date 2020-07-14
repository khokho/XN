package ge.exen.services;

import ge.exen.dto.PostEditDTO;
import ge.exen.dto.PostWriteDTO;
import ge.exen.models.Post;

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
    boolean removePost(Long postId);
}
