package az.mapacademy.announcement.dto;

import lombok.Data;

/**
 * @author : Dunay Gudratli
 * @since : 08.04.2025
 **/
@Data
public class FileResponse {
    private String name;
    private String type;
    private byte[] data;
}
