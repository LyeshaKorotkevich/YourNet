package by.gsu.yournet.service;

import by.gsu.yournet.dao.PostDao;
import by.gsu.yournet.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostDao postDao;

    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postDao.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public void savePost(Post post) {
        postDao.save(post);
    }

    @Transactional
    public Post getPostById(Long postId){
        return postDao.findById(postId);
    }

    @Transactional
    public void deletePost(Long id){
        postDao.deleteById(id);
    }
}