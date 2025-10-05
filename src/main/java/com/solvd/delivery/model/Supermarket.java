package com.solvd.delivery.model;

import com.solvd.delivery.dao.interfaces.ISupermarketDAO;

public class Supermarket implements ISupermarketDAO {
    private Long supermarketId;
    private boolean bulkDiscounts;

    public Supermarket() {}

    public Supermarket(Long supermarketId, boolean bulkDiscounts) {
        this.supermarketId = supermarketId;
        this.bulkDiscounts = bulkDiscounts;
    }

    public Long getRestaurantId() {
        return supermarketId;
    }

    public void setRestaurantId(Long supermarketId) {
        this.supermarketId = supermarketId;
    }

    public boolean isBulkDiscounts() {
        return bulkDiscounts;
    }

    public void setBulkDiscounts(boolean bulkDiscounts) {
        this.bulkDiscounts = bulkDiscounts;
    }

    @Override public String toString() {
        return "Supermarket{"+supermarketId+", offers bulk discounts: "+bulkDiscounts+"}";
    }
}
