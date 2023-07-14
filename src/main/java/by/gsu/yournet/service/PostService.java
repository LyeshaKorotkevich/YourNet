package by.gsu.yournet.service;

import by.gsu.yournet.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    void savePost(Post post);
    Post getPostById(Long postId);
    void deletePost(Long id);
}
