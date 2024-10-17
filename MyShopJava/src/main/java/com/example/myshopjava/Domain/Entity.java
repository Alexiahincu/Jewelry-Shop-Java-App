package com.example.myshopjava.Domain;


public interface Entity<ID> {
    void setId(ID id);
    ID getId();
}
