package com.solvd.delivery;

import com.solvd.delivery.model.Customer;
import com.solvd.delivery.model.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Timestamp;

public class CustomerModelTest {

    private static final Logger LOGGER = LogManager.getLogger(CustomerModelTest.class);

    @BeforeClass(alwaysRun = true)
    public void setupTestData() {
        LOGGER.info("Setting up CustomerModelTest test data");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        LOGGER.info("Cleaning up CustomerModelTest resources");
    }

    @Test(priority = 1,
            groups = {
            "smoke", "customer"
            },
            description = "Verify customer creation with valid data")
    public void verifyCustomerCreationWithValidDataTest() {
        Customer customer = new Customer(
                1L,
                "John Doe",
                "securePass123",
                "john@example.com",
                UserRole.CUSTOMER,
                new Timestamp(System.currentTimeMillis()),
                "123 Main St, New York, NY",
                "+1-555-0100");

        Assert.assertNotNull(customer, "Customer should not be null");
        Assert.assertEquals(customer.getId(), Long.valueOf(1L), "Customer ID should match");
        Assert.assertEquals(customer.getName(), "John Doe", "Username should match");
        Assert.assertEquals(customer.getEmail(), "john@example.com", "Email should match");
        Assert.assertEquals(customer.getRole(), UserRole.CUSTOMER, "Role should be CUSTOMER");
        Assert.assertEquals(customer.getAddress(), "123 Main St, New York, NY", "Address should match");
        Assert.assertEquals(customer.getPhoneNumber(), "+1-555-0100", "Phone number should match");
    }

    @DataProvider(name = "phoneNumberFormats")
    public Object[][] phoneNumberFormats() {
        return new Object[][] {
                { "+1-555-0100", true },
                { "(555) 123-4567", true },
                { "555-123-4567", true },
                { "5551234567", true },
                { "+44 20 7946 0958", true },
                { "", false },
                { null, false }
        };
    }

    @Test(dataProvider = "phoneNumberFormats",
            groups = {
            "customer", "validation"
            },
            description = "Verify customer phone number validation with various formats")
    public void verifyCustomerPhoneNumberValidationTest(String phoneNumber, boolean shouldBeValid) {
        Customer customer = new Customer();
        customer.setPhoneNumber(phoneNumber);

        if (shouldBeValid) {
            Assert.assertNotNull(customer.getPhoneNumber(),
                    "Valid phone number should be set: " + phoneNumber);
        } else {
            Assert.assertTrue(customer.getPhoneNumber() == null || customer.getPhoneNumber().isEmpty(),
                    "Invalid phone number should be null or empty");
        }
    }

    @Test(dependsOnMethods = "verifyCustomerCreationWithValidDataTest",
            timeOut = 1000,
            groups = {
            "customer"
            },
            description = "Verify customer address is not null after creation")
    public void verifyCustomerAddressNotNullTest() {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setName("Jane Smith");
        customer.setEmail("jane@example.com");
        customer.setRole(UserRole.CUSTOMER);
        customer.setAddress("456 Oak Avenue, Los Angeles, CA");
        customer.setPhoneNumber("+1-555-0200");

        Assert.assertNotNull(customer.getAddress(), "Customer address should not be null");
        Assert.assertTrue(customer.getAddress().length() > 0, "Address should have content");
        Assert.assertTrue(customer.getAddress().contains(","),
                "Address should contain comma separator");
    }
}
