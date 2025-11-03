package com.solvd.delivery.model;
public class CustomerBuilder extends UserBuilder<CustomerBuilder> {

    public CustomerBuilder() {
        this.user = new Customer(); // Replace base type instance
    }

    public CustomerBuilder withAddress(String address) {
        ((Customer) user).setAddress(address);
        return this;
    }

    public CustomerBuilder withPhoneNumber(String phone) {
        ((Customer) user).setPhoneNumber(phone);
        return this;
    }

    @Override
    public Customer build() {
        return (Customer) user;
    }
}