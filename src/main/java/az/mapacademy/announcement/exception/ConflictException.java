package az.mapacademy.announcement.exception;

/**
 * @author : Dunay Gudratli
 * @since : 18.03.2025
 **/
public class ConflictException extends RuntimeException{
    public ConflictException() {
        super();
    }
    public ConflictException(String message) {
        super(message);
    }
}
