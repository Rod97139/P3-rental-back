package com.oc.dto;

import com.oc.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalRequestDto {
    public RentalRequestDto(String name, String surface, String price, String description) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.description = description;
    }
    private String name;
    private String surface;
    private String price;
    private String picture;
    private String description;
}
