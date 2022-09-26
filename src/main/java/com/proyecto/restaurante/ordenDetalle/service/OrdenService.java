package com.proyecto.restaurante.ordenDetalle.service;

import com.proyecto.restaurante.loginRegistro.modelos.Usuario;
import com.proyecto.restaurante.ordenDetalle.models.Orden;
import com.proyecto.restaurante.ordenDetalle.repository.OrdenRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrdenService implements IOrden {

    @Autowired
    private  OrdenRepository ordenRepository;

    public OrdenService() {
    }


    
    
    
    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

//public List<Orden> findByUsuario(ClientePersona usuario) {
//		return ordenRepository.findByUsuario(usuario);
//	}
    @Override
    public Optional<Orden> findById(int id) {
        return ordenRepository.findById(id);
    }

    // 0000010
    @Override
    public String generarNumeroOrden() {
        int numero = 0;
        String numeroConcatenado = "";

        List<Orden> ordenes = findAll();

        List<Integer> numeros = new ArrayList<Integer>();

        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

        if (ordenes.isEmpty()) {
            numero = 1;
        } else {
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero < 10) { //0000001000
            numeroConcatenado = "000000000" + String.valueOf(numero);
        } else if (numero < 100) {
            numeroConcatenado = "00000000" + String.valueOf(numero);
        } else if (numero < 1000) {
            numeroConcatenado = "0000000" + String.valueOf(numero);
        } else if (numero < 10000) {
            numeroConcatenado = "0000000" + String.valueOf(numero);
        }

        return numeroConcatenado;
    }

    public boolean existsById(int id) {
        return ordenRepository.existsById(id);
    }

    public void delete(int id) {

        ordenRepository.deleteById(id);

    }

    public Optional<Orden> get(int id) {
        return ordenRepository.findById(id);
    }

    public Orden list(Orden orden) {
        return ordenRepository.save(orden);

    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findAll();
    }

    public List<Orden> list() {
       return ordenRepository.findAll();     
    }

}
