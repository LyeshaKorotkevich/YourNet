package by.gsu.yournet;

import by.gsu.yournet.model.User;
import by.gsu.yournet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthorizedUser {

    @Autowired
    private UserService userService;

    public User getUserByUsername() throws UsernameNotFoundException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findByUsername(auth.getName());

        return user;
    }
}
