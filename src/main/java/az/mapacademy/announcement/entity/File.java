package az.mapacademy.announcement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Dunay Gudratli
 * @since : 08.04.2025
 **/
@Entity
@Table(name = "FILES")
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    private String name;
    private String type;
    private LocalDateTime createdAt;

}
