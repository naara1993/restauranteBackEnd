
package com.proyecto.restaurante.ordenDetalle.repository;

import com.proyecto.restaurante.ordenDetalle.models.OrdenDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDetalleRepository  extends JpaRepository<OrdenDetalle, Integer>{

    public List<OrdenDetalle> save(List<OrdenDetalle> detalleOrden);
    
    //    public List<OrdenDetalle> save(List<OrdenDetalle> detalleOrden);
    
}
