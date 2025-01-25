package com.oc.services.impl;

import com.oc.dto.MessageRequestDto;
import com.oc.mapper.MessageMapper;
import com.oc.mapper.RentalMapper;
import com.oc.mapper.UserMapper;
import com.oc.model.Message;
import com.oc.model.Rental;
import com.oc.model.User;
import com.oc.repository.MessageRepository;
import com.oc.services.MessageService;
import com.oc.services.RentalService;
import com.oc.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final UserService userService;
    private final RentalService rentalService;
    private MessageRepository messageRepository;


    @Override
    public void createMessage(MessageRequestDto messageRequestDto) {
        Message message = MessageMapper.mapToMessage(messageRequestDto);
        User user = UserMapper.mapToUser(userService.getUserById(messageRequestDto.getUserId()));
        Rental rental = RentalMapper.mapToRental(rentalService.getRentalDtoById(messageRequestDto.getRentalId()));
        message.setRental(rental);
        message.setUser(user);
        Message savedMessage = messageRepository.save(message);

        MessageMapper.mapToMessageDto(savedMessage);
    }
}
