package com.solvd.delivery;

import com.solvd.delivery.model.*;
import com.solvd.delivery.service.impl.*;

import com.solvd.delivery.dao.factory.DAOFactory;
import com.solvd.delivery.dao.interfaces.ICustomerDAO;
import com.solvd.delivery.dao.interfaces.IUserMyBatisDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {

        DAOFactory mysqlFactory = DAOFactory.getFactory("mysql");
        DAOFactory mybatisFactory = DAOFactory.getFactory("mybatis");

        ICustomerDAO customerDAO = mysqlFactory.getCustomerDAO();
        IUserMyBatisDAO userDAO = mybatisFactory.getUserDAO();

        CustomerService customerService = new CustomerService(customerDAO);
        UserServiceMyBatis userServiceMyBatis = new UserServiceMyBatis(userDAO);

        CourierService courierService = new CourierService();
        ManagerService managerService = new ManagerService();

        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();

        PromotionService promotionService = new PromotionService();
        AchievementService achievementService = new AchievementService();

        String ordersPath = Objects.requireNonNull(Main.class.getClassLoader().getResource("xml/orders.xml")).getPath();
        String paymentsPath = Objects.requireNonNull(Main.class.getClassLoader().getResource("xml/payments.xml")).getPath();
        String paymentsXsdPath = "src/main/resources/xml/payments.xsd";

        Customer customer = new CustomerBuilder()
                .withName("JohnDoe")
                .withPassword("secret123")
                .withEmail("johndoe@example.com")
                .withRole(UserRole.CUSTOMER)
                .withCreatedAt(new Timestamp(System.currentTimeMillis()))
                .withAddress("123 Main St")
                .withPhoneNumber("555-1234")
                .build();

        Long customerId = customerService.createCustomer(customer);
        LOGGER.info("Customer created with ID: {}", customerId);

        Optional<Customer> fetchedCustomer = customerService.getCustomerById(customerId);
        fetchedCustomer.ifPresent(c -> LOGGER.info("Fetched customer: {}", c));

        Optional<Customer> customerByUsername = customerService.getCustomerByUsername("JohnDoe");
        customerByUsername.ifPresent(c -> LOGGER.info("Customer fetched by username: {}", c));

        LOGGER.info("\nAll Customers:");
        for (Customer c : customerService.getAllCustomers()) {
            LOGGER.info(c);
        }

        customer.setAddress("456 Updated St");
        boolean updatedCustomer = customerService.updateCustomer(customer);
        LOGGER.info("Customer updated? {}", updatedCustomer);

        boolean deletedCustomer = customerService.deleteCustomer(customerId);
        LOGGER.info("Customer deleted? {}", deletedCustomer);

        Courier courier = new CourierBuilder()
                .withName("FastCourier")
                .withPassword("fastpass")
                .withEmail("courier@example.com")
                .withRole(UserRole.COURIER)
                .withCreatedAt(new Timestamp(System.currentTimeMillis()))
                .withVehicleType(VehicleType.BIKE)
                .withLicensePlate("XYZ-123")
                .withAvailability(true)
                .build();

        Long courierId = courierService.createCourier(courier);
        LOGGER.info("Courier created with ID: {}", courierId);

        Optional<Courier> fetchedCourier = courierService.getCourierById(courierId);
        fetchedCourier.ifPresent(c -> LOGGER.info("Fetched courier: {}", c));

        Optional<Courier> courierByUsername = courierService.getCourierByUsername("FastCourier");
        courierByUsername.ifPresent(c -> LOGGER.info("Courier fetched by username: {}", c));

        LOGGER.info("\nAll Couriers:");
        for (Courier c : courierService.getAllCouriers()) {
            LOGGER.info(c);
        }

        courier.setAvailability(false);
        boolean updatedCourier = courierService.updateCourier(courier);
        LOGGER.info("Courier updated? {}", updatedCourier);

        boolean deletedCourier = courierService.deleteCourier(courierId);
        LOGGER.info("Courier deleted? {}", deletedCourier);

        Manager manager = new ManagerBuilder()
                .withName("AliceManager")
                .withPassword("managerpass")
                .withEmail("manager@example.com")
                .withRole(UserRole.MANAGER)
                .withCreatedAt(new Timestamp(System.currentTimeMillis()))
                .withVerified(false)
                .build();

        Long managerId = managerService.createManager(manager);
        LOGGER.info("Manager created with ID: {}", managerId);

        Optional<Manager> fetchedManager = managerService.getManagerById(managerId);
        fetchedManager.ifPresent(m -> LOGGER.info("Fetched manager: {}", m));

        Optional<Manager> managerByUsername = managerService.getManagerByUsername("AliceManager");
        managerByUsername.ifPresent(m -> LOGGER.info("Manager fetched by username: {}", m));

        LOGGER.info("\nAll Managers:");
        for (Manager m : managerService.getAllManagers()) {
            LOGGER.info(m);
        }

        manager.setVerified(true);
        boolean updatedManager = managerService.updateManager(manager);
        LOGGER.info("Manager updated? {}", updatedManager);

        boolean deletedManager = managerService.deleteManager(managerId);
        LOGGER.info("Manager deleted? {}", deletedManager);

        List<Order> orders = orderService.loadOrdersFromXml(ordersPath);

        LOGGER.info("Orders loaded from XML:");
        orders.forEach(order -> LOGGER.info(order.toString()));

        orderService.saveOrdersToXml(orders, "src/main/resources/xml/orders_out.xml");

        List<Payment> payments = paymentService.loadPaymentsWithValidation(paymentsPath, paymentsXsdPath);
        LOGGER.info("Payments validated and loaded from XML:");
        payments.forEach(LOGGER::info);

        paymentService.savePaymentsToFile(payments, "src/main/resources/xml/payments_out.xml");

        List<Promotion> promotions = promotionService.loadPromotions("src/main/resources/json/promotions.json");
        List<Achievement> achievements = achievementService.loadAchievements("src/main/resources/json/achievements.json");

        LOGGER.info("Promotions loaded from JSON:");
        promotions.forEach(promo -> LOGGER.info(promo.toString()));

        LOGGER.info("Achievements loaded from JSON:");
        achievements.forEach(ach -> LOGGER.info(ach.toString()));

        promotionService.savePromotions(promotions, "src/main/resources/json/promotions_out.json");
        achievementService.saveAchievements(achievements, "src/main/resources/json/achievements_out.json");

        LOGGER.info("=== Testing UserServiceMyBatis (MyBatis Implementation) ===");

        User user = new UserBuilder<>()
                .withName("Julian")
                .withPassword("test1234")
                .withEmail("julian@example.com")
                .withRole(UserRole.CUSTOMER)
                .withCreatedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        userServiceMyBatis.createUser(user);
        User fetchedById = userServiceMyBatis.getUserById(user.getId());
        if (fetchedById != null) {
            LOGGER.info("Fetched user by ID: {}", fetchedById);
        }

        User fetchedByEmail = userServiceMyBatis.getUserByEmail("julian@example.com");
        if (fetchedByEmail != null) {
            LOGGER.info("Fetched user by email: {}", fetchedByEmail);
        }

        List<User> allUsers = userServiceMyBatis.getAllUsers();
        LOGGER.info("All users in DB (MyBatis):");
        allUsers.forEach(u -> LOGGER.info("{}", u));

    }
}
