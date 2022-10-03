package com.proyecto.restaurante.ordenDetalle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.restaurante.loginRegistro.modelos.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Orden implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRecibida;
    private String tipoPago;
    private double total;
    private String nombre;
    private String estado;
    
    private int costoEnvio;
    private boolean envio;
    
    private int numeroTelefono;
    private String direccion;

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    @OneToMany(mappedBy = "orden")
    @JsonIgnore
    private List<OrdenDetalle> detalle;

    @ManyToOne
    @JoinColumn(name = "id_usuario")

    private Usuario usuario;

    public Orden() {
    }

    public Orden(int id, String numero, Date fechaCreacion, Date fechaRecibida, double total, String nombre, List<OrdenDetalle> detalle, Usuario usuario) {
        this.id = id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.fechaRecibida = fechaRecibida;
        this.total = total;
        this.nombre = nombre;
        this.detalle = detalle;
        this.usuario = usuario;
    }

    public Orden(String numero, Date fechaCreacion, Date fechaRecibida, double total, String nombre, List<OrdenDetalle> detalle, Usuario usuario) {
        super();
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.fechaRecibida = fechaRecibida;
        this.total = total;
        this.nombre = nombre;
        this.detalle = detalle;
        this.usuario = usuario;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(Date fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<OrdenDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<OrdenDetalle> detalle) {
        this.detalle = detalle;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(int costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public boolean Envio() {
        return envio;
    }

    public void setEnvio(boolean envio) {
        this.envio = envio;
    }
    
    

}
