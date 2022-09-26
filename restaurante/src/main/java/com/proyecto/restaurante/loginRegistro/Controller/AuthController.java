
package com.proyecto.restaurante.loginRegistro.Controller;


import com.proyecto.restaurante.SendEmail.models.EnvioEmail;
import com.proyecto.restaurante.loginRegistro.dto.JwtDto;
import com.proyecto.restaurante.loginRegistro.dto.LoginUsuario;
import com.proyecto.restaurante.loginRegistro.dto.Mensaje;
import com.proyecto.restaurante.loginRegistro.dto.NuevoUsuario;
import com.proyecto.restaurante.loginRegistro.jwt.JwtProvider;
import com.proyecto.restaurante.loginRegistro.modelos.Rol;
import com.proyecto.restaurante.loginRegistro.modelos.Usuario;
import com.proyecto.restaurante.loginRegistro.rolNombreEnum.RolNombre;
import com.proyecto.restaurante.loginRegistro.service.RolService;
import com.proyecto.restaurante.loginRegistro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;
    
       
    @Autowired
    private EnvioEmail mailService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        mailService.sendEmail(nuevoUsuario.getEmail(),"Datos de acceso","{"
                + "nombre usuario:"+nuevoUsuario.getNombreUsuario()+" /n "+"contraseña : "+nuevoUsuario.getPassword()+"}");
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
    
   
    
    
        //mostrar por id
    @GetMapping("/detail/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Integer id){
        if(!usuarioService.findId(id))  // si no existe el id
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioService.findById(id).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }
    
        
    @GetMapping("/detailName/{nombreUsuario}")
    public ResponseEntity<Usuario> getByName(@PathVariable("nombreUsuario") String nombreUsuario){
        if(!usuarioService.existsByNombreUsuario(nombreUsuario))  // si no existe el id
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
          Optional<Usuario> optionalUsuario = usuarioService.getByNombreUsuario(nombreUsuario);
          Usuario user=new Usuario();
          user=optionalUsuario.get();
        return new ResponseEntity(user, HttpStatus.OK);
    }
    
    
    
    
    
    
          
      //mostrar lista
    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario> list = usuarioService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    
    
    
    
    
    
    
    
}
