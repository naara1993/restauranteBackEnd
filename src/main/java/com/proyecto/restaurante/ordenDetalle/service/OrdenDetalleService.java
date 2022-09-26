package com.proyecto.restaurante.ordenDetalle.service;

import com.proyecto.restaurante.ordenDetalle.models.OrdenDetalle;
import com.proyecto.restaurante.ordenDetalle.repository.OrdenDetalleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service 
public class OrdenDetalleService implements IOrdenDetalle {

    @Autowired
    private OrdenDetalleRepository detalleOrdenRepository;

    public OrdenDetalleService() {
    }


 
    public List<OrdenDetalle> save(List<OrdenDetalle> detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }


    public boolean existsById(int id) {
        return detalleOrdenRepository.existsById(id);
    }

    public void delete(int id) {
        detalleOrdenRepository.deleteById(id);

    }

    public List<OrdenDetalle> list() {
        return detalleOrdenRepository.findAll();
    }

    @Override
    public OrdenDetalle save(OrdenDetalle detalleOrden) {
    return detalleOrdenRepository.save(detalleOrden);    
    }

}


