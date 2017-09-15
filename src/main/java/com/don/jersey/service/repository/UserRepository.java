package com.don.jersey.service.repository;

import com.don.jersey.model.User;

public interface UserRepository {
    User addUser(User user);

    User getUserById(long id);

    User getUserByEmail(String email);

    User deleteUser(long id);

    User updateUser(User user);
}
