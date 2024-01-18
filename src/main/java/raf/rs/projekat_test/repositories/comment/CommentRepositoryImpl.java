package raf.rs.projekat_test.repositories.comment;

import raf.rs.projekat_test.entities.Comment;
import raf.rs.projekat_test.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl extends MySqlAbstractRepository implements CommentRepository {
    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO comments (content, created_on, user_id, news_id) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setDate(2, comment.getCreatedOn());
            preparedStatement.setInt(3, comment.getUserId());
            preparedStatement.setInt(4, comment.getNewsId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return comment;
    }

    @Override
    public List<Comment> allComments() {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM comments");
            while (resultSet.next()) {
                comments.add(new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("content"),
                        resultSet.getDate("created_on"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("news_id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
            closeStatement(statement);
            closeResultSet(resultSet);
        }

        return comments;
    }

    @Override
    public Comment findComment(Integer id) {
        Comment comment = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                comment = new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("content"),
                        resultSet.getDate("created_on"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("news_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return comment;
    }

    @Override
    public Comment updateComment(Integer id, Comment comment ) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();
            preparedStatement =  connection.prepareStatement("UPDATE comments SET  content = ?, created_on = ?, user_id = ?, news_id = ? WHERE id = ?");
            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setDate(2, comment.getCreatedOn());
            preparedStatement.setInt(3, comment.getUserId());
            preparedStatement.setInt(4, comment.getNewsId());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }


        return comment;
    }

    @Override
    public List<Comment> findCommentsByNewsId(Integer id) {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE news_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                comments.add(new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("content"),
                        resultSet.getDate("created_on"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("news_id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return comments;
    }

    @Override
    public void deleteComment(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM comments WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
        }
    }
}
