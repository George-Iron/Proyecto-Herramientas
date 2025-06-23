package com.barber.demon.Controller.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoUserRequest {
    private String nombre;
    private String username;
    private String correo;
    private String password;
    private String rol; //
}
