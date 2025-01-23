package com.oc.mapper;

import com.oc.dto.UserDisplayDto;
import com.oc.dto.UserDto;
import com.oc.model.User;
import com.oc.utils.DateUtils;

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

    public static UserDisplayDto mapToUserDisplayDto(User user) {
        return new UserDisplayDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                DateUtils.formatInstant(user.getCreatedAt()),
                DateUtils.formatInstant(user.getUpdatedAt())
        );
    }
}
