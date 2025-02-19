package az.mapacademy.announcement.service;

import az.mapacademy.announcement.dao.CategoryDao;
import az.mapacademy.announcement.dto.CategoryDto;
import az.mapacademy.announcement.entity.Category;
import az.mapacademy.announcement.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        log.info("Getting categories");
        List<Category> categories = categoryDao.findAll();
        log.info("Categories found: {}", categories);

        return categoryMapper.toDtoList(categories);
    }
}
