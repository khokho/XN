package ge.exen.controllers;

import ge.exen.DAO.ExamDao;
import ge.exen.DAO.UserDAO;
import ge.exen.dto.PostWriteDTO;
import ge.exen.dto.UserLoginDTO;
import ge.exen.dto.UserRegisterDTO;
import ge.exen.models.Exam;
import ge.exen.models.Post;
import ge.exen.models.PostJSON;
import ge.exen.models.User;
import ge.exen.services.IPostsService;
import ge.exen.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostsController {
    @Autowired
    IPostsService postsService;

    @Autowired
    IUserService userService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    ExamDao examDao;

    @PostMapping("/newPost")
    public String writeNewPost(PostWriteDTO postWriteDTO){
        postsService.writeNewPost(postWriteDTO);
      //  return new RedirectView("/posts/"+postWriteDTO.getExamId());
        return "ok";
    }
    @GetMapping("/posts/{examId}")
    public String getPostsView(Model model, @PathVariable Integer examId){
//        UserRegisterDTO lekva = new UserRegisterDTO();
//        lekva.setStatus("lector");
//        lekva.setPassword("rabbit");
//        lekva.setLastName("Lekveishvili");
//        lekva.setName("giorgi");
//        lekva.setEmail("g.lekveishvili@freeuni.edu.ge");
//        userService.registerNewUser(lekva);
//        UserLoginDTO lekvaLogin = new UserLoginDTO();
//        lekvaLogin.setPassword("rabbit");
//        lekvaLogin.setEmail("g.lekveishvili@freeuni.edu.ge");
//        userService.login(lekvaLogin);
        model.addAttribute("examId", examId);
        model.addAttribute("status", userService.getCurrentUser().getStatus());
        model.addAttribute("content", "post.jsp");
        return "/template";
    }

    @ResponseBody
    @GetMapping(value = "/getPosts/{examId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostJSON> getPosts(@PathVariable Integer examId){
        List<Post> posts = postsService.getPostsByExamId(examId);
        List<PostJSON> postJSONs = new ArrayList<>();
        for (Post post : posts) {
            if(post.getExamId() != examId) continue;
            PostJSON postJSON = new PostJSON();
            postJSON.setText(post.getText());
            User lecturer = userDAO.getUser(post.getFromId());
            postJSON.setLecturer(lecturer.getName() + " " + lecturer.getLastName());
            Exam exam = examDao.get(post.getExamId());
            postJSON.setExam(exam.getFullName());
            postJSON.setDate(post.getDate());
            postJSON.setPostId(post.getPostId());
            postJSON.setAction("add");
            postJSONs.add(postJSON);
        }
        return postJSONs;
    }

    @PostMapping("/removePost/{examId}")
    public String removePost(long postId, @PathVariable Integer examId){
        postsService.removePost(postId, (long) examId);
        //return new RedirectView("/posts/"+examId);
        return "ok";
    }

    /**
     * TODO uncommnet when there is time for writing its front
    @PostMapping("/editPost/{examId}")
    public RedirectView editPost(PostEditDTO postEditDTO, @PathVariable Integer examId){
        postsService.editPost(postEditDTO);
        return new RedirectView("/posts/"+examId);
    }
     */

}
