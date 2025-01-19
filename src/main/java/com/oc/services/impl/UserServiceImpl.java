package com.oc.services.impl;

import com.oc.dto.UserDto;
import com.oc.exception.ResourceNotFoundException;
import com.oc.mapper.UserMapper;
import com.oc.model.User;
import com.oc.repository.UserRepository;
import com.oc.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = UserMapper.mapToUser(userDto);
        User SavedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(SavedUser);
    }

    @Override
    public UserDto getUserById(Integer UserId) {

        User user = userRepository.findById(UserId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + UserId)
        );

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Integer userId, UserDto updatedUser) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setUpdatedAt(updatedUser.getUpdatedAt());

        User updatedUserObj = userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        userRepository.deleteById(userId);

    }
}
