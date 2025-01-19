package com.oc.dto;

import com.oc.model.Message;
import com.oc.model.Rental;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String email;
    private String name;
    private String password;
    private Instant createdAt;
    private Instant updatedAt;
    private Set<Message> messages = new LinkedHashSet<>();
    private Set<Rental> rentals = new LinkedHashSet<>();
}
