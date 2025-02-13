package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.CategoryDao;
import az.mapacademy.announcement.dto.CategoryDto;
import az.mapacademy.announcement.entity.Category;
import az.mapacademy.announcement.mapper.CategoryMapper;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
public class CategoryService {
    private final CategoryDao categoryDao = new CategoryDao();
    private final CategoryMapper categoryMapper = new CategoryMapper();

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryDao.findAll();
        return categoryMapper.toDtoList(categories);
    }
}
