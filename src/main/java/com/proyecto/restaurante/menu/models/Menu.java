
package com.proyecto.restaurante.menu.models;

import com.proyecto.restaurante.loginRegistro.modelos.Usuario;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    
    private String title;
    private String company;
    private String image;
    private float price;

   

 @ManyToOne
     @JoinColumn(name="usuario")

 private Usuario usuario;
 
 public Menu() {
    }

        public Menu(String title, String company, String image, float price) {
        this.title = title;
        this.company = company;
        this.image = image;
        this.price = price;
    }
    public Menu(int id, String title, String company, String image, float price) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.image = image;
        this.price = price;
    }



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
    
}
