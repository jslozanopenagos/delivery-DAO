package com.solvd.delivery.model;

import java.sql.Timestamp;
public class UserBuilder<T extends UserBuilder<T>> {

    protected User user;

    public UserBuilder() {
        this.user = new User();
    }

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }

    public T withId(Long id) {
        user.setId(id);
        return self();
    }

    public T withName(String username) {
        user.setName(username);
        return self();
    }

    public T withPassword(String password) {
        user.setPassword(password);
        return self();
    }

    public T withEmail(String email) {
        user.setEmail(email);
        return self();
    }

    public T withRole(UserRole role) {
        user.setRole(role);
        return self();
    }

    public T withCreatedAt(Timestamp timestamp) {
        user.setCreatedAt(timestamp);
        return self();
    }

    public User build() {
        return user;
    }
}