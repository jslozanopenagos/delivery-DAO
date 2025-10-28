package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.User;

import java.util.List;

public interface UserMapper {
    void insert(User user);
    User findById(Long id);
    List<User> findAll();
    User findByEmail(String email); // optional
}