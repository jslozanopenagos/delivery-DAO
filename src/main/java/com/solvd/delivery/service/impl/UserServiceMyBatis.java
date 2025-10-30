package com.solvd.delivery.service.impl;

import com.solvd.delivery.model.User;
import com.solvd.delivery.dao.interfaces.IUserMyBatisDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceMyBatis {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceMyBatis.class);
    private final IUserMyBatisDAO userDao;

    public UserServiceMyBatis(IUserMyBatisDAO userDAO) {
        this.userDao = userDAO;
    }

    public void createUser(User user) {
        userDao.insert(user);
        LOGGER.info("User created successfully via MyBatis: {}", user);
    }

    public User getUserById(Long id) {
        User user = userDao.findById(id);
        if (user != null) {
            LOGGER.info("User fetched by ID {}: {}", id, user);
        } else {
            LOGGER.warn("No user found with ID: {}", id);
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = userDao.findAll();
        LOGGER.info("Fetched {} users from database (MyBatis).", users.size());
        users.forEach(u -> LOGGER.debug("→ {}", u));
        return users;
    }

    public User getUserByEmail(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            LOGGER.info("User fetched by email '{}': {}", email, user);
        } else {
            LOGGER.warn("No user found with email: {}", email);
        }
        return user;
    }
}