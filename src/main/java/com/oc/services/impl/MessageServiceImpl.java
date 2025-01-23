package com.oc.services.impl;

import com.oc.dto.MessageDto;
import com.oc.dto.MessageRequestDto;
import com.oc.exception.ResourceNotFoundException;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final UserService userService;
    private final RentalService rentalService;
    private MessageRepository messageRepository;


    @Override
    public MessageDto createMessage(MessageRequestDto messageRequestDto) {
        Message message = MessageMapper.mapToMessage(messageRequestDto);
        User user = UserMapper.mapToUser(userService.getUserById(messageRequestDto.getUser_id()));
        Rental rental = RentalMapper.mapToRental(rentalService.getRentalDtoById(messageRequestDto.getRental_id()));
        message.setRental(rental);
        message.setUser(user);
        Message savedMessage = messageRepository.save(message);

        return MessageMapper.mapToMessageDto(savedMessage);
    }
//
//    @Override
//    public MessageDto getMessageById(Integer messageId) {
//        Message message = messageRepository.findById(messageId).orElseThrow(
//                () -> new ResourceNotFoundException("Message not found with id: " + messageId)
//        );
//
//        return MessageMapper.mapToMessageDto(message);
//    }
//
//    @Override
//    public List<MessageDto> getAllMessages() {
//        List<Message> messages = messageRepository.findAll();
//
//        return messages.stream()
//                .map((message) -> MessageMapper.mapToMessageDto(message))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public MessageDto updateMessage(Integer messageId, MessageDto updatedMessage) {
//        Message message = messageRepository.findById(messageId)
//                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + messageId));
//        message.setMessage(updatedMessage.getMessage());
//        message.setUpdatedAt(updatedMessage.getUpdatedAt());
//
//        Message updatedMessageObj = messageRepository.save(message);
//
//        return MessageMapper.mapToMessageDto(updatedMessageObj);
//    }
//
//    @Override
//    public void deleteMessage(Integer messageId) {
//        Message message = messageRepository.findById(messageId)
//                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + messageId));
//        messageRepository.deleteById(messageId);
//    }
}
