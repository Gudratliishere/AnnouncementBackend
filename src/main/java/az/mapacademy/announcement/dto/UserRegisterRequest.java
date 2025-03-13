package az.mapacademy.announcement.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author : Dunay Gudratli
 * @since : 13.03.2025
 **/
@Data
public class UserRegisterRequest {
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
}
