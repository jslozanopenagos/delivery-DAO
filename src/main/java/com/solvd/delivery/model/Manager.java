package com.solvd.delivery.model;

import java.sql.Timestamp;

public class Manager extends User {
    private boolean isVerified;


    public Manager() {}

    public Manager(
            Long userId, String username,
            String userPassword, String email,
            UserRole role, Timestamp createdAt,
            Long managerId, boolean isVerified
    ) {
        super(userId, username, userPassword, email, role, createdAt);
        this.isVerified = isVerified;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    @Override
    public String toString() {
        return "Manager {" +
                "id=" + getId() +
                ", username='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role=" + getRole() +
                ", isVerified=" + isVerified +
                '}';
    }
}
