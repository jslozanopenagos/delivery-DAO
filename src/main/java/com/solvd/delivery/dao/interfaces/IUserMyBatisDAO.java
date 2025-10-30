package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.User;

import java.util.List;

public interface IUserMyBatisDAO {
    void insert(User user);
    User findById(Long id);
    User findByEmail(String email);
    List<User> findAll();
}
