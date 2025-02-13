package az.mapacademy.announcement.dao;

import az.mapacademy.announcement.config.DatabaseConfig;
import az.mapacademy.announcement.entity.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
public class CategoryDao {
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection()) {
            String sql = "SELECT * FROM categories";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("category_id");
                String name = resultSet.getString("name");

                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return categories;
    }
}
