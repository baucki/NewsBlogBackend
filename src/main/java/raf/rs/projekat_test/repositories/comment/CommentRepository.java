package raf.rs.projekat_test.repositories.comment;

import raf.rs.projekat_test.entities.Comment;

import java.util.List;

public interface CommentRepository {

    public Comment addComment(Comment comment);

    public List<Comment> allComments();

    public Comment findComment(Integer id);

    public Comment updateComment(Integer id, Comment comment);

    public List<Comment> findCommentsByNewsId(Integer id);

    public void deleteComment(Integer id);

}
