
package com.proyecto.restaurante.menu.repository;

import com.proyecto.restaurante.loginRegistro.service.UsuarioService;
import com.proyecto.restaurante.menu.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MenuRepository  extends JpaRepository<Menu, Integer>{

    public void save(UsuarioService usuario);
    
}
