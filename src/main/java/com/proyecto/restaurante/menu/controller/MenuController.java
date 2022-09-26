
package com.proyecto.restaurante.menu.controller;

import com.proyecto.restaurante.loginRegistro.dto.Mensaje;
import com.proyecto.restaurante.loginRegistro.modelos.Usuario;
import com.proyecto.restaurante.loginRegistro.service.UsuarioService;
import com.proyecto.restaurante.menu.modelDTO.MenuDTO;
import com.proyecto.restaurante.menu.models.Menu;
import com.proyecto.restaurante.menu.service.MenuServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/menu")
public class MenuController {
    
    
   @Autowired
   MenuServicio  menuService;
   
    @Autowired
    private UsuarioService usuario;
   
   //crear persona
         @PreAuthorize("hasRole('ADMIN')")
         @PostMapping("/crear")
      public ResponseEntity<?>   create(@RequestBody MenuDTO menu){
           if(menu.getTitle()==null || menu.getTitle()==""){
                   return new ResponseEntity(new Mensaje("el titulo no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }
          if(menu.getCompany()==null || menu.getCompany()==""){
                   return new ResponseEntity(new Mensaje("la compania no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }if(menu.getImage()==null || menu.getImage()==""){
                   return new ResponseEntity(new Mensaje("la imagen no debe estar vacia"), HttpStatus.BAD_REQUEST);
          }
          if(menu.getPrice()<0){
                   return new ResponseEntity(new Mensaje("el precio no puede ser negativo"), HttpStatus.BAD_REQUEST);
          }
          Menu men = new Menu (menu.getTitle(),menu.getCompany(),menu.getImage(),
          menu.getPrice()); 
          menuService.save(men);
          return new ResponseEntity(new Mensaje("producto en el menu creado"), HttpStatus.OK);          
      }
      
      
      //mostrar lista
    @GetMapping("/lista")
    public ResponseEntity<List<Menu>> list(){
        List<Menu> list = menuService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
      
    //mostrar por id
   
    @GetMapping("/detail/{id}")
    public ResponseEntity<Menu> getById(@PathVariable("id") int id){
        if(!menuService.existsById(id))  // si no existe el id
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Menu menu = menuService.getOne(id).get();  
        return new ResponseEntity(menu, HttpStatus.OK);
    }
    
    
    
    
    //eliminar por id
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!menuService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        menuService.delete(id);
        return new ResponseEntity(new Mensaje("persona eliminada"), HttpStatus.OK);
    }
    
    //actualizar persona
    
    
        @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody MenuDTO menu){
        if(!menuService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
                    if(menu.getTitle()==null || menu.getTitle()==""){
                   return new ResponseEntity(new Mensaje("el titulo no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }
          if(menu.getCompany()==null || menu.getCompany()==""){
                   return new ResponseEntity(new Mensaje("la compania no debe estar vacio"), HttpStatus.BAD_REQUEST);
          }if(menu.getImage()==null || menu.getImage()==""){
                   return new ResponseEntity(new Mensaje("la imagen no debe estar vacia"), HttpStatus.BAD_REQUEST);
          }
          if(menu.getPrice()<0){
                   return new ResponseEntity(new Mensaje("el precio no puede ser negativo"), HttpStatus.BAD_REQUEST);
          }
        
        Menu men = menuService.getOne(id).get();
        men.setTitle(menu.getTitle());
        men.setCompany(menu.getCompany());
        men.setImage(menu.getImage());
        men.setPrice(menu.getPrice());
        menuService.save(men);
        return new ResponseEntity(new Mensaje("menu producto actualizado"), HttpStatus.OK);
    }
  
    
    
    //guardarProducto
    
    
	@PostMapping("/save/{id}")
	public ResponseEntity save(@PathVariable("id") Integer id)  {
       
       Usuario user = usuario.findById(id).get();
       Menu menu = new Menu();
       menuService.setUsuario(usuario);
       menuService.save(menu);
  return new ResponseEntity(new Mensaje("guardado con exito"), HttpStatus.OK);
	}
    
        

}
