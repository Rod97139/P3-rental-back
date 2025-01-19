package com.oc.services.impl;

import com.oc.dto.MessageDto;
import com.oc.exception.ResourceNotFoundException;
import com.oc.mapper.MessageMapper;
import com.oc.model.Message;
import com.oc.repository.MessageRepository;
import com.oc.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;


    @Override
    public MessageDto createMessage(MessageDto messageDto) {
        Message message = MessageMapper.mapToMessage(messageDto);
        Message savedMessage = messageRepository.save(message);

        return MessageMapper.mapToMessageDto(savedMessage);
    }

    @Override
    public MessageDto getMessageById(Integer messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(
                () -> new ResourceNotFoundException("Message not found with id: " + messageId)
        );

        return MessageMapper.mapToMessageDto(message);
    }

    @Override
    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageRepository.findAll();

        return messages.stream()
                .map((message) -> MessageMapper.mapToMessageDto(message))
                .collect(Collectors.toList());
    }

    @Override
    public MessageDto updateMessage(Integer messageId, MessageDto updatedMessage) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + messageId));
        message.setMessage(updatedMessage.getMessage());
        message.setUpdatedAt(updatedMessage.getUpdatedAt());

        Message updatedMessageObj = messageRepository.save(message);

        return MessageMapper.mapToMessageDto(updatedMessageObj);
    }

    @Override
    public void deleteMessage(Integer messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + messageId));
        messageRepository.deleteById(messageId);
    }
}
