package az.mapacademy.announcement.controller;

import az.mapacademy.announcement.dto.CategoryDto;
import az.mapacademy.announcement.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService = new CategoryService();

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
