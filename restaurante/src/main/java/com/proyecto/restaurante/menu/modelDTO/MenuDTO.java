
package com.proyecto.restaurante.menu.modelDTO;

import javax.validation.constraints.NotBlank;

public class MenuDTO {
    
    @NotBlank
    private String title;
    @NotBlank
    private String company;
    @NotBlank
    private String image;
    @NotBlank
    private float price;

    public MenuDTO(String title, String company, String image, float price) {
        this.title = title;
        this.company = company;
        this.image = image;
        this.price = price;
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
