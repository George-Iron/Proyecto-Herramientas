package com.barber.demon.Persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barber.demon.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Optional<Usuario> findByUsernameAndActivoTrue(String username); //
    List<Usuario> findByActivoTrue();
    Optional<Usuario> findByIdAndActivoTrue(Long id);
}
