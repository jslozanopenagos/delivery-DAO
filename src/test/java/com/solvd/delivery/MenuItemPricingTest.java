package com.solvd.delivery;

import com.solvd.delivery.model.MenuItem;
import org.testng.Assert;
import org.testng.annotations.*;

public class MenuItemPricingTest {

    @DataProvider(name = "priceValidationData")
    public Object[][] priceValidationData() {
        return new Object[][] {
                { 0.01, true },   // Minimum valid price
                { 5.99, true },
                { 15.50, true },
                { 99.99, true },
                { 999.99, true },
                { 0.00, false },  // Invalid: zero price
                { -5.00, false }, // Invalid: negative price
                { -0.01, false }
        };
    }

    @Test(dataProvider = "priceValidationData",
            groups = {
            "pricing", "validation"
            },
            description = "Verify menu item price validation with valid and invalid prices")
    public void verifyMenuItemPriceValidationTest(double price, boolean shouldBeValid) {
        MenuItem item = new MenuItem();
        item.setItemId(1L);
        item.setName("Test Item");
        item.setDescription("Test Description");
        item.setPrice(price);
        item.setAvailable(true);

        if (shouldBeValid) {
            Assert.assertTrue(item.getPrice() > 0,
                    "Valid price should be positive: " + price);
            Assert.assertEquals(item.getPrice(), price, 0.001,
                    "Price should match the set value");
        } else {
            Assert.assertTrue(item.getPrice() <= 0,
                    "Invalid price should be zero or negative: " + price);
        }
    }

    @Test(enabled = true,
            groups = {"pricing"
            },
            priority = 2,
            description = "Verify menu item availability impacts ordering capability")
    public void verifyMenuItemAvailabilityImpactTest() {
        MenuItem item = new MenuItem();
        item.setItemId(2L);
        item.setName("Pepperoni Pizza");
        item.setDescription("Pizza with pepperoni and cheese");
        item.setPrice(14.99);
        item.setAvailable(true);

        Assert.assertTrue(item.isAvailable(), "Item should initially be available");
        Assert.assertTrue(item.getPrice() > 0, "Available item should have valid price");

        item.setAvailable(false);
        Assert.assertFalse(item.isAvailable(), "Item should be unavailable after toggle");
        Assert.assertTrue(item.getPrice() > 0,
                "Price should remain valid even when unavailable");

        Assert.assertEquals(item.getName(), "Pepperoni Pizza", "Name should be preserved");
        Assert.assertEquals(item.getPrice(), 14.99, 0.01, "Price should be preserved");
    }
}