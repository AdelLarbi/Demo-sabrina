package com.sabrina.demo.model.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class Purchase {

    @Id
    private long id;
    private String date;
    private String product;
    private double price;

    private ToOne<User> user;

    public Purchase() {
        /* Entity is expected to have a no-arg constructor */
    }

    public Purchase(String date, String product, double price, User user) {
        this.date = date;
        this.product = product;
        this.price = price;
        this.user.setTarget(user);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public ToOne<User> getUser() {
        return user;
    }
}
