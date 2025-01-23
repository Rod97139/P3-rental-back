package com.oc.mapper;

import com.oc.dto.MessageDto;
import com.oc.dto.MessageRequestDto;
import com.oc.model.Message;
import com.oc.model.Rental;
import com.oc.model.User;

import java.time.Instant;

public class MessageMapper {
    public static MessageDto mapToMessageDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getRental(),
                message.getUser(),
                message.getMessage(),
                message.getCreatedAt(),
                message.getUpdatedAt()
        );
    }

    public static Message mapToMessage(MessageDto messageDto) {
        return new Message(
                messageDto.getId(),
                messageDto.getRental(),
                messageDto.getUser(),
                messageDto.getMessage(),
                messageDto.getCreatedAt(),
                messageDto.getUpdatedAt()
        );
    }

    public static Message mapToMessage(MessageRequestDto messageRequestDto) {
        return new Message(
                null,
                new Rental(),
                new User(),
                messageRequestDto.getMessage(),
                Instant.now(),
                Instant.now()
        );
    }
}
