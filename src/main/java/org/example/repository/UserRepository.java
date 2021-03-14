package org.example.repository;

import org.example.classes.User;

public interface UserRepository {

    void addUser(User user);

    User findUserById(int id);

    User findUserByTaxNumber(String taxNumber);

}
