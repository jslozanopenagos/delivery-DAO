package com.solvd.delivery.model;

import com.solvd.delivery.dao.interfaces.IManagerDAO;

public class Manager {
    private Long managerId;
    private boolean isVerified;


    public Manager() {}

    public Manager(Long managerId, boolean isVerified) {
        this.managerId = managerId;
        this.isVerified = isVerified;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    @Override public String toString() {
        return "Manager{"+managerId+", verified="+isVerified+"}";
    }
}
