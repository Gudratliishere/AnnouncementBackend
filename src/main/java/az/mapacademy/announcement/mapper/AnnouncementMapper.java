package az.mapacademy.announcement.mapper;

import az.mapacademy.announcement.dto.AnnouncementResponse;
import az.mapacademy.announcement.dto.CreateAnnouncementRequest;
import az.mapacademy.announcement.dto.UpdateAnnouncementRequest;
import az.mapacademy.announcement.entity.Announcement;
import az.mapacademy.announcement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 18.02.2025
 **/
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnnouncementMapper {
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    @Mapping(target = "sellerFullName", expression = "java(mapName(announcement.getUser()))")
    AnnouncementResponse toResponse(Announcement announcement);

    List<AnnouncementResponse> toResponseList(List<Announcement> announcements);

    @Mapping(target = "announcementNumber", expression = "java(generateAnnouncementNumber())")
    @Mapping(source = "cityId", target = "city.cityId")
    @Mapping(source = "categoryId", target = "category.categoryId")
    @Mapping(target = "createdDate", expression = "java(getNow())")
    @Mapping(target = "modifiedDate", expression = "java(getNow())")
    Announcement toEntity(CreateAnnouncementRequest request);

    @Mapping(target = "modifiedDate", expression = "java(getNow())")
    void populate(UpdateAnnouncementRequest request, @MappingTarget Announcement announcement);

    default Long generateAnnouncementNumber() {
        double d = Math.random() * 100000000;
        return (long) d;
    }

    default LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    default String mapName(User user) {
        return user.getName() + " " + user.getSurname();
    }
}
