package az.mapacademy.announcement.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author : Dunay Gudratli
 * @since : 13.03.2025
 **/
@Data
public class UserUpdateRequest {
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String phoneNumber;
}
