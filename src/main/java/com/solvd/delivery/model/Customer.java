package com.solvd.delivery.model;

import com.solvd.delivery.dao.interfaces.ICustomerDao;

public class Customer implements ICustomerDao {
    private Long customerId;
    private String address;
    private String phoneNumber;

    public Customer() {}

    public Customer(Long customerId, String address, String phoneNumber) {
        this.customerId = customerId;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    @Override public String toString() {
        return "Customer{"+customerId+", addr='"+address+"'}";
    }
}
