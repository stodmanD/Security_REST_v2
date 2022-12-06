package com.stolypin.securityrest.services;

import com.stolypin.securityrest.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    void addUser(User user);

    User getUser(int id);


    void deleteUser(int id);

   User getByUsername(String username);
}


