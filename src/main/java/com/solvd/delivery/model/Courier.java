package com.solvd.delivery.model;

import com.solvd.delivery.dao.interfaces.ICourierDAO;

public class Courier {
    private Long courierId;
    private VehicleType vehicleType;
    private String licensePlate;
    private boolean availability;


    public Courier() {}

    public Courier(Long courierId, VehicleType vehicleType, String licensePlate, boolean availability) {
        this.courierId = courierId;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.availability = availability;
    }

    public Long getCourierId() {
        return courierId; }
    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override public String toString() {
        return "Courier{"+courierId+","+vehicleType+"}";
    }
}