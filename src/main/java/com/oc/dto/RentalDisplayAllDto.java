package com.oc.dto;

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
        private Number owner_id;
        private Instant createdAt;
        private Instant updatedAt;
}