package az.mapacademy.announcement.dto;

import lombok.Data;

/**
 * @author : Dunay Gudratli
 * @since : 18.02.2025
 **/
@Data
public class AnnouncementRequest {
    private String name;
    private String description;
    private Double price;
    private String phoneNumber;
    private String sellerFullName;
    private Boolean delivery;
    private Long cityId;
    private Long categoryId;
}
