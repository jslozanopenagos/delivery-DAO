package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.Customer;
import com.solvd.delivery.model.Manager;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IManagerDAO {
    Optional<Manager> findById(Long id) throws SQLException;
    List<Customer> findAll() throws SQLException;
    boolean create(Manager manager) throws SQLException;
    boolean update(Manager manager) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
