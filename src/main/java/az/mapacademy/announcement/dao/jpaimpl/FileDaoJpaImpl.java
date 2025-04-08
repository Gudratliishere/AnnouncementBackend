package az.mapacademy.announcement.dao.jpaimpl;

import az.mapacademy.announcement.dao.FileDao;
import az.mapacademy.announcement.entity.File;
import az.mapacademy.announcement.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : Dunay Gudratli
 * @since : 08.04.2025
 **/
@Component
@RequiredArgsConstructor
public class FileDaoJpaImpl implements FileDao {
    private final FileRepository fileRepository;

    @Override
    public File add(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void remove(Long id) {
        fileRepository.deleteById(id);
    }
}
