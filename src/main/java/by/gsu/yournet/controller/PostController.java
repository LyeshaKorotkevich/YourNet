package by.gsu.yournet.controller;

import by.gsu.yournet.AuthorizedUser;
import by.gsu.yournet.model.Comment;
import by.gsu.yournet.model.Post;
import by.gsu.yournet.model.User;
import by.gsu.yournet.service.CommentService;
import by.gsu.yournet.service.PostService;
import by.gsu.yournet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private AuthorizedUser authorizedUser;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "welcome";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public String createPost(@ModelAttribute("post") Post post,
                             Principal principal) {

        User user = userService.findByUsername(principal.getName());
        post.setUser(user);
        postService.savePost(post);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String showPost(@PathVariable("id") Long postId, Model model) throws IOException {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        List<Comment> comments = commentService.getAllComments(postId);
        model.addAttribute("comments", comments);

        User user = authorizedUser.getUserByUsername();
        String imageString = user.convertPhoto();
        model.addAttribute("photo", imageString);
        return "post";
    }

    @RequestMapping(value = "/post/{postId}/comment", method = RequestMethod.POST)
    public String addComment(@PathVariable("postId") Long postId,
                             @RequestParam("content") String content,
                             Principal principal) {

        User user = userService.findByUsername(principal.getName());
        Post post = postService.getPostById(postId);
        Comment comment = new Comment(content, post, user);
        commentService.saveComment(comment);
        post.setCommentCount(post.getCommentCount()+1);
        postService.savePost(post);

        return "redirect:/post/" + postId;
    }

    @RequestMapping(value = "/post/delete", method = RequestMethod.GET)
    public String deletePost(@RequestParam("id") Long postId) {postService.deletePost(postId);
        return "redirect:/welcome";
    }
}
