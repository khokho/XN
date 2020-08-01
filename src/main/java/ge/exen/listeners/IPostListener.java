package ge.exen.listeners;

import ge.exen.models.Post;

public interface IPostListener {
    /**
     * fires a signal when new post is received
     */
    void postReceived(Post post);
}
