
package com.proyecto.restaurante.loginRegistro.repository;


import com.proyecto.restaurante.loginRegistro.modelos.Rol;
import com.proyecto.restaurante.loginRegistro.rolNombreEnum.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
