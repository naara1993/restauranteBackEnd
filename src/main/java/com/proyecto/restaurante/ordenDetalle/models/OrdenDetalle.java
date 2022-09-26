package com.proyecto.restaurante.ordenDetalle.models;


import com.proyecto.restaurante.menu.models.Menu;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrdenDetalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double cantidad;
    private double precio;
    private double total;
    private String nombre;
    private String imagen;
 
    @ManyToOne
    @JoinColumn(name="id_orden")
    private Orden orden;
    
    @ManyToOne
    @JoinColumn(name="id_Menu")
    private Menu menu;

    public OrdenDetalle() {
    }

    public OrdenDetalle(int id, double cantidad, double precio, double total) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public OrdenDetalle(double cantidad, double precio, double total,String nombre,String imagen) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.nombre=nombre;
        this.imagen=imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }




    
    
    
    

}
