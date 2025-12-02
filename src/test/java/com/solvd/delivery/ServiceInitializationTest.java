package com.solvd.delivery;

import com.solvd.delivery.dao.impl.mysql.CustomerDAO;
import com.solvd.delivery.dao.interfaces.ICustomerDAO;
import com.solvd.delivery.service.impl.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class ServiceInitializationTest {

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("env", "test");
    }

    @BeforeClass
    public void beforeClass() {
        // Can set up shared resources here if needed
    }

    @Test
    public void testOrderServiceInstantiation() {
        OrderService service = new OrderService();
        Assert.assertNotNull(service);
    }

    @Test
    public void testCustomerServiceInstantiation() {
        ICustomerDAO customerDAO = new CustomerDAO();
        CustomerService service = new CustomerService(customerDAO);
        Assert.assertNotNull(service);
    }

    @AfterClass
    public void afterClass() {
        // Clean up shared resources
    }

    @AfterSuite
    public void afterSuite() {
        System.clearProperty("env");
    }
}
