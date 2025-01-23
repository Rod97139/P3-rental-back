package com.oc.services;

import com.oc.dto.MessageDto;
import com.oc.dto.MessageRequestDto;


public interface MessageService {

    MessageDto createMessage(MessageRequestDto messageDto);

}
