package az.mapacademy.announcement.mapper;

import az.mapacademy.announcement.dto.AnnouncementDto;
import az.mapacademy.announcement.entity.Announcement;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 18.02.2025
 **/
@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
    AnnouncementDto toDto(Announcement announcement);

    List<AnnouncementDto> toDtoList(List<Announcement> announcements);
}
