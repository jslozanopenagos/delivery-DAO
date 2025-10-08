package com.solvd.delivery.service.interfaces;

import com.solvd.delivery.model.Courier;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ICourierService {

    Optional<Courier> getCourierById(Long id) throws SQLException;
    Optional<Courier> getCourierByUsername(String username) throws SQLException;
    List<Courier> getAllCouriers() throws SQLException;
    Long createCourier(Courier courier) throws SQLException;
    boolean updateCourier(Courier courier) throws SQLException;
    boolean deleteCourier(Long courierId) throws SQLException;

    boolean changeCourierAvailability(Long courierId, boolean availability) throws SQLException;
}
