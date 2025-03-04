package az.mapacademy.announcement.dao;

import az.mapacademy.announcement.entity.Category;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 04.03.2025
 **/
public interface CategoryDao {
    List<Category> findAll();
}
