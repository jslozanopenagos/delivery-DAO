package com.solvd.delivery.service.interfaces;

import com.solvd.delivery.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Optional<Customer> getCustomerById(Long id) throws SQLException;
    Optional<Customer> getCustomerByUsername(String username) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
    Long createCustomer(Customer customer) throws SQLException;
    boolean updateCustomer(Customer customer) throws SQLException;
    boolean deleteCustomer(Long customerId) throws SQLException;

}
