package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.Supermarket;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public interface ISupermarketDao {
    Optional<Supermarket> findById(Long id) throws SQLException;
    List<Supermarket> findAll() throws SQLException;
    boolean create(Supermarket s) throws SQLException;
    boolean update(Supermarket s) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
