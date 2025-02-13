package az.mapacademy.announcement.mapper;

import az.mapacademy.announcement.dto.CategoryDto;
import az.mapacademy.announcement.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@Component
public class CategoryMapper {
    public CategoryDto toDto (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public List<CategoryDto> toDtoList (List<Category> categoryList){
        return categoryList.stream()
                .map(this::toDto)
                .toList();
    }
}
