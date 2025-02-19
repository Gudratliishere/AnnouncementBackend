package az.mapacademy.announcement.controller;

import az.mapacademy.announcement.dto.CategoryDto;
import az.mapacademy.announcement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@Slf4j
@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        log.info("Get all categories API is called");
        return categoryService.getAllCategories();
    }
}
