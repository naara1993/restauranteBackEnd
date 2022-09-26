
package com.proyecto.restaurante.ordenDetalle.service;

import com.proyecto.restaurante.loginRegistro.modelos.Usuario;
import com.proyecto.restaurante.ordenDetalle.models.Orden;
import java.util.List;
import java.util.Optional;

public interface IOrden {
        List<Orden> findAll();
	Optional<Orden> findById(int id);
	Orden save (Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario (Usuario usuario);
}
