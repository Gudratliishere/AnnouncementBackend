package az.mapacademy.announcement.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author : Dunay Gudratli
 * @since : 11.02.2025
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;
    private String name;
}
