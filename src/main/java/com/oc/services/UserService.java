package com.oc.services;

import com.oc.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Integer UserId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Integer userId, UserDto updatedUser);

    void deleteUser(Integer userId);

}
