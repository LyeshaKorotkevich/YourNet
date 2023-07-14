package by.gsu.yournet.dao;

import by.gsu.yournet.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDao extends JpaRepository<Post, Long> {
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<Post> findAllByOrderByCreatedAtDesc();

    Post findById(Long id);

    void deleteById(Long id);
}
