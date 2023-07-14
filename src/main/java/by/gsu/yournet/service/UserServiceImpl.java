package by.gsu.yournet.service;

import by.gsu.yournet.dao.RoleDao;
import by.gsu.yournet.dao.UserDao;
import by.gsu.yournet.model.Role;
import by.gsu.yournet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public void addFriends(User user, String username) {
        if(findByUsername(username)!= null){
            if(user.getFriends() == null) {
                Set<User> users = new HashSet<>();
                users.add(findByUsername(username));
                user.setFriends(users);
                userDao.save(user);
            }else{
                user.getFriends().add(findByUsername(username));
                userDao.save(user);
            }
        }
    }
}
