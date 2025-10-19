package com.solvd.delivery.model;

import java.time.LocalDateTime;

public class Promotion {

    private Long id;
    private String code;
    private String description;
    private double discountPercentage;
    private String validFrom;
    private String validUntil;

    private boolean active;

    public Promotion() {}

    public Promotion(Long id, String code, String description, double discountPercentage,
                     String validFrom, String validUntil, boolean active) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(double discountPercentage) { this.discountPercentage = discountPercentage; }

    public String getValidFrom() { return validFrom; }
    public void setValidFrom(String validFrom) { this.validFrom = validFrom; }

    public String getValidUntil() { return validUntil; }
    public void setValidUntil(String validUntil) { this.validUntil = validUntil; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", validFrom=" + validFrom +
                ", validUntil=" + validUntil +
                ", active=" + active +
                '}';
    }
}