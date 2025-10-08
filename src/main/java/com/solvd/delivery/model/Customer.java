package com.solvd.delivery.model;

import java.sql.Timestamp;

public class Customer extends User {
    private String address;
    private String phoneNumber;

    public Customer() {}

    public Customer(
            Long userId, String username,
            String userPassword, String email,
            UserRole role, Timestamp createdAt,
            String address, String phoneNumber
    ) {
        super(userId, username, userPassword, email, role, createdAt);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", username='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role=" + getRole() +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
