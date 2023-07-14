package by.gsu.yournet.controller;

import by.gsu.yournet.AuthorizedUser;
import by.gsu.yournet.dao.UserDao;
import by.gsu.yournet.model.Post;
import by.gsu.yournet.service.PostService;
import by.gsu.yournet.validator.UserValidator;
import by.gsu.yournet.model.User;
import by.gsu.yournet.service.SecurityService;
import by.gsu.yournet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private AuthorizedUser authorizedUser;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) throws IOException {
        User user = authorizedUser.getUserByUsername();
        String imageString = user.convertPhoto();
        model.addAttribute("photo", imageString);
        List<Post> posts;
        posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping(value = {"/friends"}, method = RequestMethod.GET)
    public String friends(Model model) throws IOException {
        User user = authorizedUser.getUserByUsername();
        String imageString = user.convertPhoto();
        model.addAttribute("photo", imageString);
        return "friends";
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public String profile(Model model) throws IOException {
        User user = authorizedUser.getUserByUsername();
        model.addAttribute("user", user);

        String imageString = user.convertPhoto();
        model.addAttribute("photo", imageString);
        return "profile";
    }

    @RequestMapping(value = "/profile/add", method = RequestMethod.POST)
    public String addProfile(
        @ModelAttribute("login") String login,
        @ModelAttribute("info") String info,
        @ModelAttribute("country") String country,
        @ModelAttribute("city") String city,
        @ModelAttribute("birthday") Date birthday,
        @ModelAttribute("club") String club,
        @ModelAttribute("player") String player,
        @RequestParam(value="photo", required=false)
                MultipartFile photo
    ) throws IOException {
            User user = authorizedUser.getUserByUsername();
            user.setUsername(login);
            user.setInfo(info);
            user.setCountry(country);
            user.setCity(city);
            user.setBirthday(birthday);
            user.setClub(club);
            user.setPlayer(player);

            try {
                if(!photo.isEmpty()) {
                    if(photo.getContentType().equals("image/jpeg") || photo.getContentType().equals("image/png")){
                        user.setPhoto(photo.getBytes());
                    }
                }
            } catch (Exception e) {
                System.out.println("Something wrong with photo ");
                e.printStackTrace();
            }

            userDao.save(user);
        return "redirect:/profile";
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public String userProfile(@RequestParam("username") String username, Model model) throws IOException {
        User userProfile = userService.findByUsername(username);
        model.addAttribute("userProfile", userProfile);

        User user = authorizedUser.getUserByUsername();
        String imageString = user.convertPhoto();
        model.addAttribute("photo", imageString);

        return "user_profile";
    }

}
