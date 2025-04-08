package az.mapacademy.announcement.repository;

import az.mapacademy.announcement.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Dunay Gudratli
 * @since : 08.04.2025
 **/
public interface FileRepository extends JpaRepository<File, Long> {
}
