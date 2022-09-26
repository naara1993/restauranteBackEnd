
package com.proyecto.restaurante.menu.service;

import com.proyecto.restaurante.loginRegistro.service.UsuarioService;
import com.proyecto.restaurante.menu.models.Menu;
import com.proyecto.restaurante.menu.repository.MenuRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  
public class MenuServicio {
    
       
    @Autowired
   MenuRepository  menuRepository;

    
    
    
    public MenuServicio() {
    }
    
       
     public Optional<Menu> get(Integer id){
        return menuRepository.findById(id);
    }
     public List<Menu> list(){
        return menuRepository.findAll();
    }
     
        public Optional<Menu> getOne(int id){
        return menuRepository.findById(id);
    }

    
   
      public void  save(Menu producto){
     
        menuRepository.save(producto);
    }
    

    public void delete(int id){
        menuRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return menuRepository.existsById(id);
    } 

    public Optional<Menu>findById(Integer id) {
    return menuRepository.findById(id);
    }

    public void setUsuario(UsuarioService usuario) {
         menuRepository.save(usuario);
    }
}
