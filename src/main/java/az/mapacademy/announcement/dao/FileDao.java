package az.mapacademy.announcement.dao;

import az.mapacademy.announcement.entity.File;

/**
 * @author : Dunay Gudratli
 * @since : 08.04.2025
 **/
public interface FileDao {
    File add(File file);

    void remove(Long id);
}
