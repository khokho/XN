package ge.exen.services;

import ge.exen.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostListener implements IPostListener{

    @Autowired
    public void setPostsService(PostsService postsService) {
        postsService.addPostListener(this);
    }

    @Override
    public void postReceived(Post post){

    }


}
