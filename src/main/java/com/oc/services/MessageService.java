package com.oc.services;

import com.oc.dto.MessageRequestDto;


public interface MessageService {

    void createMessage(MessageRequestDto messageDto);

}
