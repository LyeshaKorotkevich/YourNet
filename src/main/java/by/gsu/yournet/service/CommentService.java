package by.gsu.yournet.service;

import by.gsu.yournet.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments(Long id);
    void saveComment(Comment comment);
}
