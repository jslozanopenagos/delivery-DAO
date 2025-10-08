package com.solvd.delivery.service.impl;

import com.solvd.delivery.dao.impl.mysql.CustomerDAO;
import com.solvd.delivery.model.Customer;
import com.solvd.delivery.service.interfaces.ICustomerService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerService implements ICustomerService {
    private final CustomerDAO COSTUMERDAO = new CustomerDAO();

    @Override
    public Optional<Customer> getCustomerById(Long id) throws SQLException {
        return COSTUMERDAO.findById(id);
    }

    @Override
    public Optional<Customer> getCustomerByUsername(String username) throws SQLException {
        return COSTUMERDAO.findByUsername(username);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return COSTUMERDAO.findAll();
    }

    @Override
    public Long createCustomer(Customer customer) throws SQLException {

        if (!customer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        return COSTUMERDAO.create(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        return COSTUMERDAO.update(customer);
    }

    @Override
    public boolean deleteCustomer(Long customerId) throws SQLException {
        return COSTUMERDAO.delete(customerId);
    }
}
