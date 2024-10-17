package com.example.myshopjava.Repository;

import com.example.myshopjava.Domain.Product;

import java.util.List;

public interface ProductRepository extends Repository<Integer, Product> {
    public List<Product> findByName(String name);
    public void update(Integer id, Product newp);
    public void updateQuantity(String name, Integer newQuantity);
    public void updatePrice(String name, Integer newPrice);
    public Product find(Integer id);
}
