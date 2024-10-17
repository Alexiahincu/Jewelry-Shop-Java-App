package com.example.myshopjava.Service;

import com.example.myshopjava.Domain.User;

public interface IService {
    User login(String username, String password, IObserver iobs) throws Exception;
    void logout(User user, IObserver client) throws Exception;

}
