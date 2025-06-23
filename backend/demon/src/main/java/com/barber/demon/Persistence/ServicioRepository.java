package com.barber.demon.Persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barber.demon.Model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio,Long>{
    Optional<Servicio> findByIdAndActivoTrue(Long id);
    List<Servicio> findByActivoTrue();
}
