package com.solvd.delivery;

import com.solvd.delivery.dao.impl.mysql.CustomerDAO;
import com.solvd.delivery.dao.impl.mysql.CourierDAO;
import com.solvd.delivery.dao.impl.mysql.ManagerDAO;
import com.solvd.delivery.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            // --- TEST CUSTOMER ---
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = new Customer();
            customer.setName("JohnDoe");
            customer.setPassword("secret123");
            customer.setEmail("johndoe@example.com");
            customer.setRole(UserRole.CUSTOMER);
            customer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            customer.setAddress("123 Main St");
            customer.setPhoneNumber("555-1234");

            Long customerId = customerDAO.create(customer);
            LOGGER.info("Customer created with ID: {}",customerId);

            Optional<Customer> fetchedCustomer = customerDAO.findById(customerId);
            fetchedCustomer.ifPresent(c ->  LOGGER.info("Fetched customer: {}",c));

            Optional<Customer> customerByUsername = customerDAO.findByUsername("JohnDoe");
            customerByUsername.ifPresent(c -> LOGGER.info("Customer fetched by username: {}",c));

            LOGGER.info("\nAll Customers:");
            for (Customer c : customerDAO.findAll()) {
                LOGGER.info(c);
            }

            customer.setAddress("456 Updated St");
            boolean updatedCustomer = customerDAO.update(customer);
            LOGGER.info("Customer updated? " + updatedCustomer);

            boolean deletedCustomer = customerDAO.delete(customerId);
            LOGGER.info("Customer deleted? " + deletedCustomer);


            // --- TEST COURIER ---
            CourierDAO courierDAO = new CourierDAO();
            Courier courier = new Courier();
            courier.setName("FastCourier");
            courier.setPassword("fastpass");
            courier.setEmail("courier@example.com");
            courier.setRole(UserRole.COURIER);
            courier.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            courier.setVehicleType(VehicleType.BIKE);
            courier.setLicensePlate("XYZ-123");
            courier.setAvailability(true);

            Long courierId = courierDAO.create(courier);
            LOGGER.info("Courier created with ID: {}",courierId);

            Optional<Courier> fetchedCourier = courierDAO.findById(courierId);
            fetchedCourier.ifPresent(c ->  LOGGER.info("Fetched courier: {}",c));

            Optional<Courier> courierByUsername = courierDAO.findByUsername("FastCourier");
            courierByUsername.ifPresent(c ->  LOGGER.info("Courier fetched by username: {}", c));

            LOGGER.info("\nAll Couriers:");
            for (Courier c : courierDAO.findAll()) {
                LOGGER.info(c);
            }

            courier.setAvailability(false);
            boolean updatedCourier = courierDAO.update(courier);
            LOGGER.info("Courier updated? {}",updatedCourier);

            boolean deletedCourier = courierDAO.delete(courierId);
            LOGGER.info("Courier deleted? {}",deletedCourier);


            // --- TEST MANAGER ---
            ManagerDAO managerDAO = new ManagerDAO();
            Manager manager = new Manager();
            manager.setName("AliceManager");
            manager.setPassword("managerpass");
            manager.setEmail("manager@example.com");
            manager.setRole(UserRole.MANAGER);
            manager.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            manager.setVerified(false);

            Long managerId = managerDAO.create(manager);
            LOGGER.info("Manager created with ID: " + managerId);

            Optional<Manager> fetchedManager = managerDAO.findById(managerId);
            fetchedManager.ifPresent(m ->  LOGGER.info("Fetched manager: {}", m));

            Optional<Manager> managerByUsername = managerDAO.findByUsername("AliceManager");
            managerByUsername.ifPresent(m ->  LOGGER.info("Manager fetched by username: {}", m));

            LOGGER.info("\nAll Managers:");
            for (Manager m : managerDAO.findAll()) {
                LOGGER.info(m);
            }

            manager.setVerified(true);
            boolean updatedManager = managerDAO.update(manager);
            LOGGER.info("Manager updated? {}", updatedManager);

            boolean deletedManager = managerDAO.delete(managerId);
            LOGGER.info("Manager deleted? {}", deletedManager);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
