package az.mapacademy.announcement.entity;

import az.mapacademy.announcement.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author : Dunay Gudratli
 * @since : 13.03.2025
 **/
@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean locked;
    private Boolean enabled;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
