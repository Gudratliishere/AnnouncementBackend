package az.mapacademy.announcement.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author : Dunay Gudratli
 * @since : 25.02.2025
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse<T> {
    T data;
    String message;
}
