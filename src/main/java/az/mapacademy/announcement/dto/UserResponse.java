package az.mapacademy.announcement.dto;

import az.mapacademy.announcement.enums.Role;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author : Dunay Gudratli
 * @since : 13.03.2025
 **/
@Data
public class UserResponse {
    private Long userId;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String phoneNumber;
    private String email;
    private String username;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
