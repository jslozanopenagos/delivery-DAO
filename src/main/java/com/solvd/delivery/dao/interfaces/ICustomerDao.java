package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.Customer;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public interface ICustomerDao {
    Optional<Customer> findById(Long id) throws SQLException;
    List<Customer> findAll() throws SQLException;
    boolean create(Customer customer) throws SQLException;
    boolean update(Customer customer) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
