package com.oc.controllers;

import com.oc.dto.MessageRequestDto;
import com.oc.dto.ServerResponseMessageDto;
import com.oc.dto.UserDto;
import com.oc.services.JWTService;
import com.oc.services.MessageService;
import com.oc.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final UserService userService;
    private MessageService messageService;
    private JWTService jwtService;


    @PostMapping
    public ResponseEntity<ServerResponseMessageDto> sendMessage(
            @RequestBody MessageRequestDto messageRequestDto,
            HttpServletRequest request
    ) {

        String token = request.getHeader("Authorization").substring(7);

        String email = jwtService.getSubjectFromToken(token);
        UserDto user = userService.getUserByEmail(email);

        ServerResponseMessageDto response = new ServerResponseMessageDto();

        if (messageRequestDto.getUserId() != user.getId()) {
            response.setMessage("You are not allowed to send a message to another user");
            return ResponseEntity.badRequest().body(response);
        }

        messageService.createMessage(messageRequestDto);

        response.setMessage("Message sent with success");
        return ResponseEntity.ok(response);
    }
}
