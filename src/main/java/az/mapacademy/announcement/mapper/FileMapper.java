package az.mapacademy.announcement.mapper;

import az.mapacademy.announcement.dto.FileResponse;
import az.mapacademy.announcement.entity.File;
import org.mapstruct.Mapper;

/**
 * @author : Dunay Gudratli
 * @since : 08.04.2025
 **/
@Mapper(componentModel = "spring")
public interface FileMapper {
    FileResponse toFileResponse(File file, byte[] data);
}
