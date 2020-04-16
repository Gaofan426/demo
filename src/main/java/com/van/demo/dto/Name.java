package com.van.demo.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Name {
    private Integer id;
    private String name;
}
