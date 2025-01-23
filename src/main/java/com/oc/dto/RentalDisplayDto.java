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
public class RentalDisplayDto {
    private Integer id;
    private String name;
    private Number surface;
    private Number price;
    private String[] picture;
    private String description;
    private Number owner_id;
    private Instant created_at;
    private Instant updated_at;
}
