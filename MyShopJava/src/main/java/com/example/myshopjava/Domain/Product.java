package com.example.myshopjava.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@jakarta.persistence.Entity
@Table( name = "Products" )
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product implements Entity<Integer>, Serializable {
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private Integer price;

    @Serial
    private static final long serialVersionUID = 1000L;

    public Product() {
        id = quantity = price = 0;
        name = description = "default";
    }

    public Product(Integer id, String name, String description, Integer quantity, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String toString(){
        return "Produs { " + id +
                ", nume: " + name +
                ", cantitate: " + quantity.toString() +
                ", pret: " + price.toString() + " }";
    }

}
