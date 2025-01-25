package com.oc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalDisplayAllDto {
        private Integer id;
        private String name;
        private Number surface;
        private Number price;
        private String picture;
        private String description;
        @JsonProperty("owner_id")
        private Number ownerId;
        @JsonProperty("created_at")
        private Instant createdAt;
        @JsonProperty("updated_at")
        private Instant updatedAt;
}