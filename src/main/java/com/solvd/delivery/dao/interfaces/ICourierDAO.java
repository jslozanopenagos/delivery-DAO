package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.Courier;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public interface ICourierDAO {
    Optional<Courier> findById(Long id) throws SQLException;
    List<Courier> findAll() throws SQLException;
    boolean create(Courier courier) throws SQLException;
    boolean update(Courier courier) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
