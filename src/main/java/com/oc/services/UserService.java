package com.oc.services;

import com.oc.dto.UserDisplayDto;
import com.oc.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Integer UserId);

    UserDto getUserByEmail(String email);

    UserDisplayDto getUserDisplayById(Integer userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Integer userId, UserDto updatedUser);

    void deleteUser(Integer userId);

    UserDisplayDto getUserDisplayByEmail(String email);
}
