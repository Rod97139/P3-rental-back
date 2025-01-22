package com.oc.dto;

import com.oc.model.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.Set;

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
    private Instant createdAt;
    private Instant updatedAt;
}
