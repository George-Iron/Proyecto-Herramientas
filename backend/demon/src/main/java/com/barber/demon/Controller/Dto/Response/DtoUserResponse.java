package com.barber.demon.Controller.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoUserResponse {
    private Long id;
    private String nombre;
    private String username;
    private String correo;
    private String rol;
}
