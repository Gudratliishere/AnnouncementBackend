package az.mapacademy.announcement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Dunay Gudratli
 * @since : 18.03.2025
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
}
