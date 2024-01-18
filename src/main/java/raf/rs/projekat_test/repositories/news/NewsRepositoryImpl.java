package raf.rs.projekat_test.repositories.news;

import raf.rs.projekat_test.entities.News;
import raf.rs.projekat_test.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl extends MySqlAbstractRepository implements NewsRepository {
    @Override
    public News addNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO news (title, content, created_on, views, user_id, category_id) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setDate(3, news.getCreatedOn());
            preparedStatement.setInt(4, news.getViews());
            preparedStatement.setInt(5, news.getUserId());
            preparedStatement.setInt(6, news.getCategoryId());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return news;
    }

    @Override
    public List<News> allNews() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM news");
            while (resultSet.next()) {
                news.add(new News(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("created_on"),
                        resultSet.getInt("views"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("category_id")
                        ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
            closeStatement(statement);
            closeResultSet(resultSet);
        }

        return news;
    }

    @Override
    public News findNews(Integer id) {
        News news = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                news = new News(
                  resultSet.getInt("id"),
                  resultSet.getString("title"),
                  resultSet.getString("content"),
                  resultSet.getDate("created_on"),
                  resultSet.getInt("views"),
                  resultSet.getInt("user_id"),
                  resultSet.getInt("category_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return news;
    }

    @Override
    public void incViews(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE news SET views = views + 1 WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
        }

    }

    @Override
    public News updateNews(Integer id, News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE news SET title = ?, content = ?, created_on = ?, views = ?, user_id = ?, category_id = ? WHERE id = ?");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setDate(3, news.getCreatedOn());
            preparedStatement.setInt(4, news.getViews());
            preparedStatement.setInt(5, news.getUserId());
            preparedStatement.setInt(6, news.getCategoryId());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
            closeStatement(preparedStatement);
        }

        return news;
    }

    @Override
    public void deleteNews(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM news WHERE id = ?");
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
