package by.gsu.yournet.dao;

import by.gsu.yournet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findById(Long id);

}
