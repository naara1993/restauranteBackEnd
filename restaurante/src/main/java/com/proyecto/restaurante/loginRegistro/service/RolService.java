
package com.proyecto.restaurante.loginRegistro.service;



import com.proyecto.restaurante.loginRegistro.modelos.Rol;
import com.proyecto.restaurante.loginRegistro.repository.RolRepository;
import com.proyecto.restaurante.loginRegistro.rolNombreEnum.RolNombre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
