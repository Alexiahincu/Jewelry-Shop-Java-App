package com.example.myshopjava.Service;


import com.example.myshopjava.Domain.User;
import com.example.myshopjava.Repository.TransactionRepository;
import com.example.myshopjava.Repository.ProductRepository;
import com.example.myshopjava.Repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {

    UserRepository userRepository;
    ProductRepository productRepository;
    TransactionRepository entryRepository;

    private Map<String, IObserver> loggedClients = new HashMap<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);


    public Service(UserRepository userRepository, ProductRepository productRepository, TransactionRepository entryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.entryRepository = entryRepository;
    }

    public User login(String username, String password, IObserver iobs){
        User user = userRepository.getByCredentials(username, password);
        if(user != null) {
            loggedClients.put(user.getUsername(), iobs);
            return user;
        }
        return null;
    }

    @Override
    public void logout(User user, IObserver client) throws Exception {
        loggedClients.remove(user.getUsername());
    }

}
