package com.barber.demon.Service.Jwt;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.barber.demon.Controller.Dto.DtoAuth;
import com.barber.demon.Controller.Dto.DtoJwt;
import com.barber.demon.Controller.Dto.Request.DtoUserRequest;
import com.barber.demon.Model.Usuario;
import com.barber.demon.Model.Enums.Rol;
import com.barber.demon.Persistence.UsuarioRepository;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public DtoJwt login(DtoAuth dtoUsuarioAuth){
        //Neccesitamos crear el objeto de tipo Authenticate que contenga las credenciales
        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken
        (dtoUsuarioAuth.getUsername(), dtoUsuarioAuth.getPassword());
        //Nos apoyamos de nuestro componente AuthenticationManager para autenticar al usuario
        Authentication authentication=authenticationManager.authenticate(authToken);
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        //Generar el token JWT
        String token=jwtService.createToken(userDetails);
        return new DtoJwt(token); //lo pasa la controller 
    }
    
    public Usuario register(DtoUserRequest request){
        Usuario usuario=new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setUsername(request.getUsername());
        usuario.setContrasena(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(Rol.valueOf(request.getRol().toUpperCase()));
        return usuarioRepository.save(usuario); 
    } 
}
