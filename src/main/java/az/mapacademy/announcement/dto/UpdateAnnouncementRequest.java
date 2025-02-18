package az.mapacademy.announcement.dto;

import lombok.Data;

/**
 * @author : Dunay Gudratli
 * @since : 18.02.2025
 **/
@Data
public class UpdateAnnouncementRequest {
    private String name;
    private String description;
    private Double price;
    private String sellerFullName;
    private Boolean delivery;
}
