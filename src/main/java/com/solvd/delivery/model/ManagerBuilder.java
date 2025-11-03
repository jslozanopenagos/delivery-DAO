package com.solvd.delivery.model;
public class ManagerBuilder extends UserBuilder<ManagerBuilder> {

    public ManagerBuilder() {
        this.user = new Manager();
    }

    public ManagerBuilder withVerified(boolean verified) {
        ((Manager) user).setVerified(verified);
        return this;
    }

    @Override
    public Manager build() {
        return (Manager) user;
    }
}