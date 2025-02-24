package com.oc.dto;

import com.oc.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {
    private Integer id;
    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String picture;
    private String description;
    private User owner;
    private Instant createdAt;
    private Instant updatedAt;
}
