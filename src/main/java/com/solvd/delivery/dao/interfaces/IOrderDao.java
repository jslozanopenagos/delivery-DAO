package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.Order;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public interface IOrderDao {
    Optional<Order> findById(Long id) throws SQLException;
    List<Order> findAll() throws SQLException;
    Long create(Order order) throws SQLException;
    boolean update(Order order) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
