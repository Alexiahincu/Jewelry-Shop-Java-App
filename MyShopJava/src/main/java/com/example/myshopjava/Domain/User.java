package com.example.myshopjava.Domain;

import jakarta.persistence.*;

@jakarta.persistence.Entity
@Table( name = "Users" )
public class User implements Entity<Integer> {

    private Integer id;
    private String username;
    private String password;

    public User() {
        id = 0;
        username = "default";
        password = "default";
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    @Id
    @Column(name = "id")
    //   @GenericGenerator(name="increment", strategy = "increment")
    public Integer getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
