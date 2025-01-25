package com.oc.controllers;

import com.oc.dto.MessageRequestDto;
import com.oc.dto.UserDto;
import com.oc.services.JWTService;
import com.oc.services.MessageService;
import com.oc.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final UserService userService;
    private MessageService messageService;
    private JWTService jwtService;


    @PostMapping
    public ResponseEntity<?> sendMessage(
            @RequestBody MessageRequestDto messageRequestDto,
            HttpServletRequest request
    ) {

        String token = request.getHeader("Authorization").substring(7);


        String email = jwtService.getSubjectFromToken(token);
        UserDto user = userService.getUserByEmail(email);

        Map<String, String> response = new HashMap<>();

        if (messageRequestDto.getUserId() != user.getId()) {
            response.put("message", "You are not allowed to send a message to another user");
            return ResponseEntity.badRequest().body(response);
        }

        messageService.createMessage(messageRequestDto);

        response.put("message", "Message sent with success");
        return ResponseEntity.ok(response);
    }
}
