package az.mapacademy.announcement.mapper;

import az.mapacademy.announcement.dto.UserRegisterRequest;
import az.mapacademy.announcement.dto.UserResponse;
import az.mapacademy.announcement.dto.UserUpdateRequest;
import az.mapacademy.announcement.entity.User;
import az.mapacademy.announcement.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

/**
 * @author : Dunay Gudratli
 * @since : 13.03.2025
 **/
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = Role.class
)
public interface UserMapper {
    UserResponse toResponse(User user);

    @Mapping(target = "createdDate", expression = "java(getNow())")
    @Mapping(target = "modifiedDate", expression = "java(getNow())")
    @Mapping(target = "locked", constant = "false")
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "role", expression = "java(Role.USER)")
    User toUser(UserRegisterRequest request);

    @Mapping(target = "modifiedDate", expression = "java(getNow())")
    void populate(UserUpdateRequest request, @MappingTarget User user);

    default LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
