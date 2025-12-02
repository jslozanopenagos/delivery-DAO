package com.solvd.delivery.model;

import java.sql.Timestamp;

public class Courier extends User {
    private VehicleType vehicleType;
    private String licensePlate;
    private boolean availability;


    public Courier() {}

    public Courier(
            Long userId, String username,
            String userPassword, String email,
            UserRole role, Timestamp createdAt,
            VehicleType vehicleType, String licensePlate, boolean availability
    ) {
        super(userId, username, userPassword, email, role, createdAt);
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.availability = availability;
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

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailable(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Courier {" +
                "id=" + getId() +
                ", username='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role=" + getRole() +
                ", vehicleType=" + getVehicleType() +
                ", licensePlate='" + getLicensePlate() + '\'' +
                ", availability=" + availability +
                '}';
    }
}