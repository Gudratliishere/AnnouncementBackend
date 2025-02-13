package az.mapacademy.announcement.dao;

import az.mapacademy.announcement.config.DatabaseConfig;
import az.mapacademy.announcement.constant.QueryConstants;
import az.mapacademy.announcement.entity.Category;
import org.springframework.stereotype.Repository;

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
@Repository
public class CategoryDao {
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(QueryConstants.GET_CATEGORY_LIST_QUERY);
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
