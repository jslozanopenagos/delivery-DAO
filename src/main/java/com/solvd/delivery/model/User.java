package com.solvd.delivery.model;

import com.solvd.delivery.dao.impl.mysql.BaseDAO;
import com.solvd.delivery.dao.interfaces.IUserDAO;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class User {
    private Long userId;
    private String username;
    private String userPassword;
    private String email;
    private UserRole role;
    private Timestamp createdAt;

    public User() {}

    public User(Long userId, String username, String userPassword, String email, UserRole role, Timestamp createdAt) {
        this.userId = userId;
        this.username = username;
        this.userPassword = userPassword;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username='" + username + '\'' + ", role=" + role + '}';
    }
}
