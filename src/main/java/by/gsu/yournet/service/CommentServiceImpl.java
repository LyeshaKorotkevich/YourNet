package by.gsu.yournet.service;

import by.gsu.yournet.dao.CommentDao;
import by.gsu.yournet.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentDao commentDao;

    @Transactional(readOnly = true)
    public List<Comment> getAllComments(Long id) {
        return commentDao.findByPostIdOrderByCreatedAtDesc(id);
    }

    @Transactional
    public void saveComment(Comment comment) {
        commentDao.save(comment);
    }
}
