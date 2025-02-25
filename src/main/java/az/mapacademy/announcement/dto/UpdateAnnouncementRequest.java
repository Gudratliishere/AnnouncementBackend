package az.mapacademy.announcement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author : Dunay Gudratli
 * @since : 18.02.2025
 **/
@Data
public class UpdateAnnouncementRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;
    private String description;

    @NotNull(message = "Price can not be null")
    @Min(value = 0, message = "Price must be greater than or equal to zero")
    private Double price;
    private String sellerFullName;
    private Boolean delivery;
}
