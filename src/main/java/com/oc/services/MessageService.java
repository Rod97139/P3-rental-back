package com.oc.services;

import com.oc.dto.MessageDto;
import com.oc.dto.MessageRequestDto;

import java.util.List;

public interface MessageService {

    MessageDto createMessage(MessageRequestDto messageDto);

//    MessageDto getMessageById(Integer messageId);
//
//    List<MessageDto> getAllMessages();
//
//    MessageDto updateMessage(Integer messageId, MessageDto updatedMessage);
//
//    void deleteMessage(Integer messageId);
}
