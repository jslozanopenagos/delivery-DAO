package com.solvd.delivery.service.impl;

import com.solvd.delivery.model.Customer;
import com.solvd.delivery.dao.interfaces.ICustomerDAO;
import com.solvd.delivery.service.interfaces.ICustomerService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerService implements ICustomerService {
    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class);
    private final ICustomerDAO customerDAO;

    public CustomerService(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) throws SQLException {
        return customerDAO.findById(id);
    }

    @Override
    public Optional<Customer> getCustomerByUsername(String username) throws SQLException {
        return customerDAO.findByUsername(username);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.findAll();
    }

    @Override
    public Long createCustomer(Customer customer) throws SQLException {

        if (!customer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        return customerDAO.create(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        return customerDAO.update(customer);
    }

    @Override
    public boolean deleteCustomer(Long customerId) throws SQLException {
        return customerDAO.delete(customerId);
    }
}
