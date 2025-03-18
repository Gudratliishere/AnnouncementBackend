package az.mapacademy.announcement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author : Dunay Gudratli
 * @since : 18.03.2025
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginRequest {
    @NotBlank(message = "Username can not be blank")
    String username;
    @NotBlank(message = "Password can not be blank")
    String password;
}
