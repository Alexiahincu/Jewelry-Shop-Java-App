package com.example.myshopjava.Repository;


import com.example.myshopjava.Domain.Entity;

import java.util.List;

public interface Repository<ID, T extends Entity<ID>> {
    void add(T elem);
    List<T> getAll();
}
