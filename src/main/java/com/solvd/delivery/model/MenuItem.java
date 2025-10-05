package com.solvd.delivery.model;

import com.solvd.delivery.dao.interfaces.IMenuItemDao;

import java.sql.Timestamp;

public class MenuItem implements IMenuItemDao {
    private Long itemId;
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;
    private Timestamp createdAt;


    public MenuItem() {}

    public MenuItem(
            Long itemId, String name,
            String description, double price,
            boolean isAvailable, Timestamp createdAt
    ) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
    }
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override public String toString() {
        return "MenuItem{"+itemId+":"+name+"}";
    }
}