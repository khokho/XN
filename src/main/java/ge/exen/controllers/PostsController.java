package ge.exen.controllers;

import ge.exen.dto.PostWriteDTO;
import ge.exen.services.IPostsService;
import ge.exen.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostsController {
    @Autowired
    IPostsService postsService;

    @PostMapping("/newPost")
    public RedirectView writeNewPost(PostWriteDTO postWriteDTO){
        postsService.writeNewPost(postWriteDTO);
        return new RedirectView("/posts");
    }
    @GetMapping("/posts")
    public String getPosts(Model model){
        model.addAttribute(postsService.getPostsByUserId());
        return "post";
    }
}
