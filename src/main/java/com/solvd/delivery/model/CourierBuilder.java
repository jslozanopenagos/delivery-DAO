package com.solvd.delivery.model;
public class CourierBuilder extends UserBuilder<CourierBuilder> {

    public CourierBuilder() {
        this.user = new Courier(); // Override base instance
    }

    public CourierBuilder withVehicleType(VehicleType vehicleType) {
        ((Courier) user).setVehicleType(vehicleType);
        return this;
    }

    public CourierBuilder withLicensePlate(String plate) {
        ((Courier) user).setLicensePlate(plate);
        return this;
    }

    public CourierBuilder withAvailability(boolean availability) {
        ((Courier) user).setAvailable(availability);
        return this;
    }

    @Override
    public Courier build() {
        return (Courier) user;
    }
}

