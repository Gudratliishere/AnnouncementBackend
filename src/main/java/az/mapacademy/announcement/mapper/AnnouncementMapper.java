package az.mapacademy.announcement.mapper;

import az.mapacademy.announcement.dto.AnnouncementDto;
import az.mapacademy.announcement.entity.Announcement;

import java.util.List;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
public class AnnouncementMapper {
    private final CityMapper cityMapper = new CityMapper();
    private final CategoryMapper categoryMapper = new CategoryMapper();

    public AnnouncementDto toDto(Announcement announcement) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setAnnouncementId(announcement.getAnnouncementId());
        announcementDto.setName(announcement.getName());
        announcementDto.setDescription(announcement.getDescription());
        announcementDto.setAnnouncementNumber(announcement.getAnnouncementNumber());
        announcementDto.setPrice(announcement.getPrice());
        announcementDto.setPhoneNumber(announcement.getPhoneNumber());
        announcementDto.setSellerFullName(announcement.getSellerFullName());
        announcementDto.setDelivery(announcement.getDelivery());
        announcementDto.setCreatedDate(announcement.getCreatedDate());
        announcementDto.setModifiedDate(announcement.getModifiedDate());
        announcementDto.setCity(cityMapper.toDto(announcement.getCity()));
        announcementDto.setCategory(categoryMapper.toDto(announcement.getCategory()));

        return announcementDto;
    }

    public List<AnnouncementDto> toDtoList (List<Announcement> announcements) {
        return announcements.stream()
                .map(this::toDto)
                .toList();
    }
}
