
package com.proyecto.restaurante.ordenDetalle.repository;


import com.proyecto.restaurante.ordenDetalle.models.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    
}
