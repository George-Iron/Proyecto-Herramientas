package com.barber.demon.Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DtoAuth {
    private String username;
    private String password;
}
