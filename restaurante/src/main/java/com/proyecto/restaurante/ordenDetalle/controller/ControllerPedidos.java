
package com.proyecto.restaurante.ordenDetalle.controller;

import com.proyecto.restaurante.loginRegistro.dto.Mensaje;
import com.proyecto.restaurante.loginRegistro.modelos.Usuario;
import com.proyecto.restaurante.loginRegistro.service.UsuarioService;
import com.proyecto.restaurante.menu.models.Menu;
import com.proyecto.restaurante.menu.service.MenuServicio;
import com.proyecto.restaurante.ordenDetalle.models.Orden;
import com.proyecto.restaurante.ordenDetalle.models.OrdenDetalle;
import com.proyecto.restaurante.ordenDetalle.service.OrdenDetalleService;
import com.proyecto.restaurante.ordenDetalle.service.OrdenService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gestionPedidos")
@CrossOrigin(origins = "*")
public class ControllerPedidos {
    
    
    
    
    
    @Autowired
    private MenuServicio menu;
    @Autowired
    private OrdenService ordenService;
    @Autowired
    private OrdenDetalleService ordenDetalleService;
    @Autowired
    private UsuarioService usuario;


    
    private double sum;


	List<OrdenDetalle> detalles = new ArrayList<OrdenDetalle>();


	Orden orden = new Orden();
        

    @PostMapping("/cart/{id}/{cantidad}")
    public ResponseEntity<OrdenDetalle>  addCart(@PathVariable int id,@PathVariable Integer cantidad) {
        double sumaTotal = 0;
          OrdenDetalle detalleOrden=new OrdenDetalle(); 
            Menu men = menu.get(id).get();
  //          Optional<Menu> optionalProducto = menu.get(Id);
   //         men = optionalProducto.get();
            detalleOrden.setCantidad(cantidad);
            detalleOrden.setNombre(men.getTitle());
            detalleOrden.setImagen(men.getImage());
            detalleOrden.setPrecio(men.getPrice());
            detalleOrden.setTotal(men.getPrice() * cantidad);
            detalleOrden.setMenu(men);  
            int idMenu = men.getId();
            boolean ingresado = detalles.stream().anyMatch(p -> p.getMenu().getId() == idMenu);
            if (!ingresado) {
                detalles.add(detalleOrden);
            }  
            sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
            orden.setTotal(sumaTotal);
           orden.setNombre(men.getTitle());
          //  ordenDetalleService.save(detalles);
            return new ResponseEntity(detalles, HttpStatus.OK);
    }  
        
    
    
    
    
    // quitar un elemento del carrito productos
    @GetMapping("/delete/cart/{id}")
    public ResponseEntity<List<OrdenDetalle>> deleteProductoCart(@PathVariable("id") Integer id) {
        // lista nueva de prodcutos
        List<OrdenDetalle> ordenesNueva = new ArrayList<OrdenDetalle>();
        detalles.stream().filter(detalleOrden -> (detalleOrden.getMenu().getId() != id )).forEachOrdered(detalleOrden -> {
            ordenesNueva.add(detalleOrden);
        });
        // poner la nueva lista con los elementos restantes
        detalles = ordenesNueva;
        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        return new ResponseEntity(new Mensaje("elemento eliminado del menu"), HttpStatus.OK);
    }
    
    
        
    
   @GetMapping("/deleteList")
    public ResponseEntity<List<OrdenDetalle>> borrarLista() {
          List<OrdenDetalle> ordenesNueva = new ArrayList<OrdenDetalle>();
          detalles=ordenesNueva;
        return new ResponseEntity(detalles, HttpStatus.OK);
    }
    
    
    
    
    
    
    
    // guardar la orden 
    @GetMapping("/save/{id}/{pago}")
    public ResponseEntity<List<Orden>> saveOrder(@PathVariable("id") Integer id,@PathVariable("pago") String pago) {
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());
        Usuario user = usuario.findById(id).get();
        orden.setUsuario(user);
        orden.setTipoPago(pago);
        orden.setNombre("menu");
        ordenService.save(orden);
     
        
        List<Orden> lista = new ArrayList<>();
        lista=ordenService.findByUsuario(user);     
        //guardar detalles
        detalles.stream().map(dt -> {
            dt.setOrden(orden);
            return dt;
        }).forEachOrdered(dt -> {
            ordenDetalleService.save(dt);
        });
        ///limpiar lista y orden
        orden = new Orden();
        detalles.clear();
        return new ResponseEntity(new Mensaje("orden creada"), HttpStatus.OK);

        
    }

    
     
   @GetMapping("/lista")
    public ResponseEntity<List<OrdenDetalle>> list() {
        List<OrdenDetalle> list = ordenDetalleService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    
  @GetMapping("/listaOrdenesUsuario/{id}")
    public ResponseEntity<List<Orden>> listaOrdenesUser(@PathVariable("id") Integer id) {
             Usuario user = usuario.findById(id).get();
             List<Orden> list = ordenService.list();
             for(int i=0;i<list.size();i++){
                if(list.get(i).getUsuario().getId()==user.getId()){
       return new ResponseEntity((list), HttpStatus.OK);
                }
             }
        
        return new ResponseEntity(new Mensaje("error"),HttpStatus.BAD_REQUEST);       
    }

        
     @GetMapping("/listaOrden")
    public ResponseEntity<List<Orden>> listOrden() {
        List<Orden> list = ordenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
                

 
                
    
    @GetMapping("/lis")
    public ResponseEntity<OrdenDetalle>  addlist() {
            
            return new ResponseEntity(detalles, HttpStatus.OK);
    }
        
           @GetMapping("/l")
    public ResponseEntity<OrdenDetalle>  l() {
            List<OrdenDetalle> deta = new ArrayList<OrdenDetalle>();
            deta=ordenDetalleService.list();
            return new ResponseEntity(deta, HttpStatus.OK);
    }
        
        
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!ordenService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        
       
            List<OrdenDetalle> deta = new ArrayList<OrdenDetalle>();
            deta=ordenDetalleService.list();
         Orden o=new Orden();
         o=ordenService.findById(id).get();
     //   ordenService.delete(id);
                 for(int i=0;i<deta.size();i++){
                       OrdenDetalle d= deta.get(i);
           if(d.getOrden().getId()==o.getId()){
               ordenDetalleService.delete(d.getId());
         }
         }
                 ordenService.delete(id);
        return new ResponseEntity(new Mensaje(" eliminado"), HttpStatus.OK);
    }
        
        
        
}
