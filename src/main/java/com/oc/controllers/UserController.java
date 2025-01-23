package com.oc.controllers;

import com.oc.dto.UserDisplayDto;
import com.oc.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDisplayDto getUserById(@PathVariable Integer userId) {
        return userService.getUserDisplayById(userId);
    }
}
