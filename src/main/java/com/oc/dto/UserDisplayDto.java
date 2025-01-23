package com.oc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDisplayDto {
    private Integer id;
    private String name;
    private String email;
    private String created_at;
    private String updated_at;
}
