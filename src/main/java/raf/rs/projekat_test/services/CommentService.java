package raf.rs.projekat_test.services;

import raf.rs.projekat_test.entities.Category;
import raf.rs.projekat_test.entities.Comment;
import raf.rs.projekat_test.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return this.commentRepository.addComment(comment);
    }

    public List<Comment> allComments() {
        return this.commentRepository.allComments();
    }

    public Comment findComment(Integer id) {
        return this.commentRepository.findComment(id);
    }

    public Comment updateComment(Integer id, Comment comment) {
        return this.commentRepository.updateComment(id, comment);
    }

    public List<Comment> findCommentsByNewsId(Integer id) {
        return this.commentRepository.findCommentsByNewsId(id);
    }

    public void deleteComment(Integer id) {
        this.commentRepository.deleteComment(id);
    }

}
