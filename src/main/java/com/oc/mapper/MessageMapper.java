package com.oc.mapper;

import com.oc.dto.MessageDto;
import com.oc.model.Message;

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
}
