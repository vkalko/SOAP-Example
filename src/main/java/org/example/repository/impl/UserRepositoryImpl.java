package org.example.repository.impl;

import org.example.classes.User;
import org.example.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final List<User> usersList = new ArrayList<>();

    @PostConstruct
    private void insertUsers() {

        User user1 = new User();
        user1.setUserId(1);
        user1.setUserFirstName("FirstName1");
        user1.setUserLastName("LastName1");
        user1.setUserTaxNumber("565-3333-11");

        User user2 = new User();
        user2.setUserId(2);
        user2.setUserFirstName("FirstName2");
        user2.setUserLastName("LastName2");
        user2.setUserTaxNumber("731-44412-90");

        User user3 = new User();
        user3.setUserId(3);
        user3.setUserFirstName("FirstName3");
        user3.setUserLastName("LastName3");
        user3.setUserTaxNumber("120-9891-62");

        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);

    }

    public void addUser(@NonNull User user) {
        usersList.add(user);
    }

    public User findUserById(int id) {
        return usersList.stream().filter(user ->
                user.getUserId() == id)
                .findFirst()
                .orElse(null);
    }

    public User findUserByTaxNumber(@NonNull String taxNumber) {
        return usersList.stream().filter(user ->
                user.getUserTaxNumber().equals(taxNumber))
                .findFirst()
                .orElse(null);
    }


}
