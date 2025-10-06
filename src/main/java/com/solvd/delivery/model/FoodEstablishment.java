package com.solvd.delivery.model;

import com.solvd.delivery.dao.interfaces.IFoodEstablishmentDAO;

import java.sql.Timestamp;

public class FoodEstablishment {
    private Long establishmentId;
    private String name;
    private String address;
    private String phoneNumber;
    private String openHours;
    private boolean isOpen;
    private double rating;
    private int ratingCount;
    private Timestamp createdAt;


    public FoodEstablishment() {}

    public FoodEstablishment(
            Long establishmentId, String name,
            String address, String phoneNumber,
            String openHours, boolean isOpen,
            double rating, int ratingCount, Timestamp createdAt
    ){
        this.establishmentId = establishmentId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.openHours = openHours;
        this.isOpen = isOpen;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.createdAt = createdAt;
    }

    public Long getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(Long establishmentId) {
        this.establishmentId = establishmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override public String toString() {
        return "Food Eestablishment{"+establishmentId+":"+name+"}";
    }
}
