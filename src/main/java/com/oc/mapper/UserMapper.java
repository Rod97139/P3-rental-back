package com.oc.mapper;

import com.oc.dto.UserDto;
import com.oc.model.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt()
        );
    }
}
