package assignment;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MySqlArticleRepository implements ArticleRepository {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    private final String MYSQL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/article_lists";
    private final String MYSQL_USERNAME = "root";
    private final String MYSQL_PASSWORD = "";

    @Override
    public ArrayList<Article> findAll() {
        ArrayList<Article> articles = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            String prepareSql = "select * from articles where status = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(prepareSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String baseUrl = resultSet.getString("base_url");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String content = resultSet.getString("content");
                String thumbnail = resultSet.getString("thumbnail");
                String createdAt = resultSet.getString("create_at");
                String updatedAt = resultSet.getString("update_at");
                String deletedAt = resultSet.getString("delete_at");
                int status = resultSet.getInt("status");
                Article article = new Article();
                article.setId(id);
                article.setBaseUrl(baseUrl);
                article.setTitle(title);
                article.setDescription(description);
                article.setContent(content);
                article.setThumbnail(thumbnail);
                article.setCreatedAt(LocalDate.parse(createdAt, formatter));
                article.setUpdatedAt(LocalDate.parse(updatedAt, formatter));
                article.setDeletedAt(LocalDate.parse(deletedAt, formatter));
                article.setStatus(status);
                articles.add(article);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error. Please try again.");
        }
        return articles;
    }

    @Override
    public Article findByUrl(String url) {
        Article article = null;
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            String prepareSql = "select * from users where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(prepareSql);
            preparedStatement.setLong(1, article.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String baseUrl = resultSet.getString("base_url");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String content = resultSet.getString("content");
                String thumbnail = resultSet.getString("thumbnail");
                String createdAt = resultSet.getString("create_at");
                String updatedAt = resultSet.getString("update_at");
                String deletedAt = resultSet.getString("delete_at");
                int status = resultSet.getInt("status");
                article = new Article();
                article.setBaseUrl(baseUrl);
                article.setTitle(title);
                article.setDescription(description);
                article.setContent(content);
                article.setThumbnail(thumbnail);
                article.setCreatedAt(LocalDate.parse(createdAt, formatter));
                article.setUpdatedAt(LocalDate.parse(updatedAt, formatter));
                article.setDeletedAt(LocalDate.parse(deletedAt, formatter));
                article.setStatus(status);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error. Please try again.");
        }
        return article;
    }

    @Override
    public Article save(Article article) {
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            String prepareSql =
                    "insert into users " + "(base_url, title, description, content, thumbnail, created_at, updated_at, deleted_at, status) " + "values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(prepareSql);
            preparedStatement.setString(1, article.getBaseUrl());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getDescription());
            preparedStatement.setString(4, article.getContent());
            preparedStatement.setString(5, article.getThumbnail());
            preparedStatement.setString(6, article.getCreatedAt().toString());
            preparedStatement.setString(7, article.getUpdatedAt().toString());
            preparedStatement.setString(8, article.getDeletedAt().toString());
            preparedStatement.setInt(9, article.getStatus());
            preparedStatement.execute();
            System.out.println("Save success!");
            // 4. Đóng kết nối.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error. Please try again");
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Article update(Article article) {
        return null;
    }

    @Override
    public void deleteByUrl(String url) {
    }
}
