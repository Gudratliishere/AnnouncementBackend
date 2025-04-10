package az.mapacademy.announcement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Dunay Gudratli
 * @since : 11.02.2025
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
    private Long cityId;
    private String name;
}
