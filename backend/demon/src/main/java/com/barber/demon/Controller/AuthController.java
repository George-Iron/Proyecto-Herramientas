package com.barber.demon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barber.demon.Controller.Dto.DtoAuth;
import com.barber.demon.Controller.Dto.DtoJwt;
import com.barber.demon.Controller.Dto.Request.DtoUserRequest;
import com.barber.demon.Controller.Dto.Response.DtoUserResponse;
import com.barber.demon.Model.Usuario;
import com.barber.demon.Service.Jwt.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<DtoJwt> login(@RequestBody DtoAuth dtoUsuario){
            DtoJwt dtoJwt = authenticationService.login(dtoUsuario);
            return ResponseEntity.ok(dtoJwt);
    }

    @PostMapping("/register")
    public ResponseEntity<DtoUserResponse> register(@RequestBody DtoUserRequest request){
        Usuario usuario=authenticationService.register(request);
        DtoUserResponse response=new DtoUserResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setUsername(usuario.getUsername());
        response.setCorreo(usuario.getCorreo());
        response.setRol(usuario.getRol().name()); 
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }    

}
