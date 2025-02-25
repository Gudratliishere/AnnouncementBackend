package az.mapacademy.announcement.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * @author : Dunay Gudratli
 * @since : 13.02.2025
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnnouncementResponse {
    Long announcementId;
    String name;
    String description;
    Long announcementNumber;
    Double price;
    String phoneNumber;
    String sellerFullName;
    Boolean delivery;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;
    CityDto city;
    CategoryDto category;
}
