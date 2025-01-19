package com.oc.services;

import com.oc.dto.MessageDto;

import java.util.List;

public interface MessageService {

    MessageDto createMessage(MessageDto messageDto);

    MessageDto getMessageById(Integer messageId);

    List<MessageDto> getAllMessages();

    MessageDto updateMessage(Integer messageId, MessageDto updatedMessage);

    void deleteMessage(Integer messageId);
}
