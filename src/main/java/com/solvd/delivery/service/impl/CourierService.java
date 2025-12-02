package com.solvd.delivery.service.impl;

import com.solvd.delivery.dao.impl.mysql.CourierDAO;
import com.solvd.delivery.model.Courier;
import com.solvd.delivery.service.interfaces.ICourierService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourierService implements ICourierService {
    private final CourierDAO COURIERDAO = new CourierDAO();

    @Override
    public Optional<Courier> getCourierById(Long id) throws SQLException {
        return COURIERDAO.findById(id);
    }

    @Override
    public Optional<Courier> getCourierByUsername(String username) throws SQLException {
        return COURIERDAO.findByUsername(username);
    }

    @Override
    public List<Courier> getAllCouriers() throws SQLException {
        return COURIERDAO.findAll();
    }

    @Override
    public Long createCourier(Courier courier) throws SQLException {
        return COURIERDAO.create(courier);
    }

    @Override
    public boolean updateCourier(Courier courier) throws SQLException {
        return COURIERDAO.update(courier);
    }

    @Override
    public boolean deleteCourier(Long courierId) throws SQLException {
        return COURIERDAO.delete(courierId);
    }

    @Override
    public boolean changeCourierAvailability(Long courierId, boolean availability) throws SQLException {
        Optional<Courier> optCourier = COURIERDAO.findById(courierId);
        if (optCourier.isPresent()) {
            Courier courier = optCourier.get();
            courier.setAvailable(availability);
            return COURIERDAO.update(courier);
        }
        return false;
    }
}
