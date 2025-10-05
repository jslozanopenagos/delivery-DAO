package com.solvd.delivery.model;

import com.solvd.delivery.dao.interfaces.IRestaurantDao;

public class Restaurant implements IRestaurantDao {
    private Long restaurantId;
    private String cuisineType;

    public Restaurant() {}

    public Restaurant(Long restaurantId, String cuisineType) {
        this.restaurantId = restaurantId;
        this.cuisineType = cuisineType;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    @Override public String toString() {
        return "Restaurant{"+restaurantId+","+cuisineType+"}";
    }
}