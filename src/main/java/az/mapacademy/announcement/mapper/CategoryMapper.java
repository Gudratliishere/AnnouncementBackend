package az.mapacademy.announcement.mapper;

import az.mapacademy.announcement.dto.CategoryDto;
import az.mapacademy.announcement.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 18.02.2025
 **/
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categories);
}
