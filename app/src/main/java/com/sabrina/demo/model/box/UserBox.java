package com.sabrina.demo.model.box;

import com.sabrina.demo.ObjectBox;
import com.sabrina.demo.model.entity.User;
import com.sabrina.demo.model.entity.User_;

import io.objectbox.Box;
import io.objectbox.exception.UniqueViolationException;

public class UserBox {

    private Box<User> userBox;

    public UserBox() {
        userBox = ObjectBox.get().boxFor(User.class);
    }

    public void put(User user) {
        try {
            userBox.put(user);
        } catch (UniqueViolationException e) {
            // users are removed at the end of each session
            // ..so this exception should never be triggered
        }
    }

    public boolean isExist(String username, String password) {
        return userBox.query()
                .equal(User_.username, username)
                .and()
                .equal(User_.password, password)
                .build()
                .count() == 1;
    }

    public User findUserByUsername(String username) {
        return userBox.query()
                .equal(User_.username, username)
                .build()
                .findUnique();
    }
}