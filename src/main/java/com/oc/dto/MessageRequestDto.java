package com.oc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {
        private String message;
        @JsonProperty("user_id")
        private int userId;
        @JsonProperty("rental_id")
        private int rentalId;
}
