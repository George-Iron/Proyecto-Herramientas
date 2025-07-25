package com.barber.demon.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.barber.demon.Model.Usuario;
import com.barber.demon.Persistence.UsuarioRepository;

public class InfoUserDetailsService implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userinfo=usuarioRepository.findByUsernameAndActivoTrue(username);
        return userinfo.map(InfoUserDetails::new) //para retornar un UserDetails
            .orElseThrow(() -> new UsernameNotFoundException("user not found " +username));
    }
}
