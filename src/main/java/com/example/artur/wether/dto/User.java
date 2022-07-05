package com.example.artur.wether.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String buildDate;
    private String group;
    private String name;
    private String version;
}
