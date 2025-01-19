package com.oc.dto;

import com.oc.model.Rental;
import com.oc.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Integer id;
    private Rental rental;
    private User user;
    private String message;
    private Instant createdAt;
    private Instant updatedAt;
}
