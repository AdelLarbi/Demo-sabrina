package com.sabrina.demo.model.entity;


import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Unique;
import io.objectbox.relation.ToMany;

@Entity
public class User {

    @Id
    private long id;
    @Unique
    private String username;
    private String password;

    @Backlink(to = "user")
    private ToMany<Purchase> purchases;

    public User() {
        /* Entity is expected to have a no-arg constructor */
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ToMany<Purchase> getPurchases() {
        return purchases;
    }
}
