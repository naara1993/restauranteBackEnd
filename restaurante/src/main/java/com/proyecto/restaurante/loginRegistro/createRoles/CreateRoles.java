
package com.proyecto.restaurante.loginRegistro.createRoles;


import com.proyecto.restaurante.loginRegistro.modelos.Rol;
import com.proyecto.restaurante.loginRegistro.rolNombreEnum.RolNombre;
import com.proyecto.restaurante.loginRegistro.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        
 /*  Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
*/
       
    }
}