package com.solvd.delivery;

import com.solvd.delivery.model.*;

import com.solvd.delivery.service.impl.*;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {

        CustomerService customerService = new CustomerService();
        CourierService courierService = new CourierService();
        ManagerService managerService = new ManagerService();

        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();

        PromotionService promotionService = new PromotionService();
        AchievementService achievementService = new AchievementService();

        String ordersPath = Objects.requireNonNull(Main.class.getClassLoader().getResource("orders.xml")).getPath();
        String paymentsPath = Objects.requireNonNull(Main.class.getClassLoader().getResource("payments.xml")).getPath();
        String paymentsXsdPath = "src/main/resources/payments.xsd";

        Customer customer = new Customer();
        customer.setName("JohnDoe");
        customer.setPassword("secret123");
        customer.setEmail("johndoe@example.com");
        customer.setRole(UserRole.CUSTOMER);
        customer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        customer.setAddress("123 Main St");
        customer.setPhoneNumber("555-1234");

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

        Courier courier = new Courier();
        courier.setName("FastCourier");
        courier.setPassword("fastpass");
        courier.setEmail("courier@example.com");
        courier.setRole(UserRole.COURIER);
        courier.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        courier.setVehicleType(VehicleType.BIKE);
        courier.setLicensePlate("XYZ-123");
        courier.setAvailability(true);

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

        Manager manager = new Manager();
        manager.setName("AliceManager");
        manager.setPassword("managerpass");
        manager.setEmail("manager@example.com");
        manager.setRole(UserRole.MANAGER);
        manager.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        manager.setVerified(false);

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

        orderService.saveOrdersToXml(orders, "src/main/resources/orders_out.xml");

        List<Payment> payments = paymentService.loadPaymentsWithValidation(paymentsPath, paymentsXsdPath);
        LOGGER.info("Payments validated and loaded from XML:");
        payments.forEach(LOGGER::info);

        paymentService.savePaymentsToFile(payments, "src/main/resources/payments_out.xml");

        List<Promotion> promotions = promotionService.loadPromotions("src/main/resources/promotions.json");
        List<Achievement> achievements = achievementService.loadAchievements("src/main/resources/achievements.json");

        LOGGER.info("Promotions loaded from JSON:");
        promotions.forEach(promo -> LOGGER.info(promo.toString()));

        LOGGER.info("Achievements loaded from JSON:");
        achievements.forEach(ach -> LOGGER.info(ach.toString()));

        promotionService.savePromotions(promotions, "src/main/resources/promotions_out.json");
        achievementService.saveAchievements(achievements, "src/main/resources/achievements_out.json");
    }
}
