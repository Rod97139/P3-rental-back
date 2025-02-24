package com.oc.controllers;

import com.oc.dto.TokenResponseDto;
import com.oc.dto.UserDisplayDto;
import com.oc.dto.UserLoginRequestDto;
import com.oc.dto.UserDto;
import com.oc.services.JWTService;
import com.oc.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;
    private JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDto> register(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto(savedUser.getEmail(), savedUser.getPassword());
        return new ResponseEntity<>(jwtService.generateToken(userLoginRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
       return new ResponseEntity<>(jwtService.generateToken(userLoginRequestDto), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDisplayDto> me(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getSubjectFromToken(token);
        return new ResponseEntity<>(userService.getUserDisplayByEmail(email), HttpStatus.OK);
    }
}
