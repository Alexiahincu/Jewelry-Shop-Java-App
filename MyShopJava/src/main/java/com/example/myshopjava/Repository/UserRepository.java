package com.example.myshopjava.Repository;


import com.example.myshopjava.Domain.User;

public interface UserRepository extends Repository<Integer, User> {
    User getByCredentials(String username, String password);

}
