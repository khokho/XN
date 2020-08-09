package ge.exen.listeners;

import ge.exen.dto.PostEditDTO;
import ge.exen.models.Post;

public interface IPostListener {
    /**
     * fires a signal when new post is received
     */
    void postReceived(Post post);

    /**
     * fires a signal when the post is removed
     */
    void postRemoved(long postId, long examId);

    void postEdited(PostEditDTO postEditDTO, long examId);
}
