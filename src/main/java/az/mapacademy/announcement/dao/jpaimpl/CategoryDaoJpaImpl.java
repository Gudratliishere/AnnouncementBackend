package az.mapacademy.announcement.dao.jpaimpl;

import az.mapacademy.announcement.dao.CategoryDao;
import az.mapacademy.announcement.entity.Category;
import az.mapacademy.announcement.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 04.03.2025
 **/
@Slf4j
@Repository("categoryDaoJpaImpl")
@RequiredArgsConstructor
public class CategoryDaoJpaImpl implements CategoryDao {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        log.info("Find all categories method is called from Jpa Impl of CategoryDao");

        return categoryRepository.findAll();
    }
}
