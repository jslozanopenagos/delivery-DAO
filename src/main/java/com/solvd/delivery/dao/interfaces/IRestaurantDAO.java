package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.Restaurant;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public interface IRestaurantDAO {
    Optional<Restaurant> findById(Long id) throws SQLException;
    List<Restaurant> findAll() throws SQLException;
    boolean create(Restaurant r) throws SQLException;
    boolean update(Restaurant r) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
