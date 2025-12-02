package com.solvd.delivery;

import com.solvd.delivery.model.Order;
import com.solvd.delivery.model.OrderStatus;
import com.solvd.delivery.model.DeliveryStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Timestamp;

public class OrderValidationTest {

    private Order testOrder;

    @BeforeMethod(alwaysRun = true)
    public void setupOrder() {
        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setCustomerId(100L);
        testOrder.setCourierId(200L);
        testOrder.setEstablishmentId(300L);
        testOrder.setDeliveryAddress("789 Pine Street, Chicago, IL");
        testOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        System.out.println("Order initialized for test");
    }

    @DataProvider(name = "totals")
    public Object[][] totals() {
        return new Object[][] {
                // item1, item2, item3, expectedTotal
                { 15.99, 8.50, 12.25, 36.74 },
                { 10.00, 5.00, 5.00, 20.00 },
                { 9.99, 9.99, 9.99, 29.97 },
                { 0.01, 0.01, 0.01, 0.03 },
                { 100.00, 200.00, 300.00, 600.00 }
        };
    }

    @Test(dataProvider = "totals",
            groups = {
            "regression", "order"}
            , priority = 2,
            description = "Verify order total price calculation is accurate")
    public void verifyOrderTotalPriceCalculationTest(double itemPrice1,
                                                     double itemPrice2,
                                                     double itemPrice3,
                                                     double expectedTotal
    ) {
        double calculated = itemPrice1 + itemPrice2 + itemPrice3;

        testOrder.setTotalPrice(calculated);

        Assert.assertEquals(testOrder.getTotalPrice(), expectedTotal, 0.01,
                "Order total price must match sum of items");

        Assert.assertTrue(testOrder.getTotalPrice() > 0,
                "Total price should always be positive");
    }

    @DataProvider(name = "orderStatusTransitions")
    public Object[][] orderStatusTransitions() {
        return new Object[][] {
                { OrderStatus.PENDING, DeliveryStatus.SCHEDULED },
                { OrderStatus.ACCEPTED, DeliveryStatus.ASSIGNED },
                { OrderStatus.PROCESSING, DeliveryStatus.ASSIGNED },
                { OrderStatus.PROCESSING, DeliveryStatus.ON_THE_WAY },
                { OrderStatus.ACCEPTED, DeliveryStatus.DELIVERED },
                { OrderStatus.CANCELLED, DeliveryStatus.SCHEDULED }
        };
    }

    @Test(dataProvider = "orderStatusTransitions",
            groups = {
            "regression", "order"
            },
            description = "Verify valid order and delivery status transitions")
    public void verifyOrderStatusTransitionsTest(OrderStatus orderStatus, DeliveryStatus deliveryStatus) {
        testOrder.setOrderStatus(orderStatus);
        testOrder.setDeliveryStatus(deliveryStatus);

        Assert.assertNotNull(testOrder.getOrderStatus(), "Order status should not be null");
        Assert.assertNotNull(testOrder.getDeliveryStatus(), "Delivery status should not be null");
        Assert.assertEquals(testOrder.getOrderStatus(), orderStatus,
                "Order status should match expected value");
        Assert.assertEquals(testOrder.getDeliveryStatus(), deliveryStatus,
                "Delivery status should match expected value");

        if (orderStatus == OrderStatus.ACCEPTED && deliveryStatus == DeliveryStatus.DELIVERED) {
            Assert.assertEquals(deliveryStatus, DeliveryStatus.DELIVERED,
                    "Accepted orders can have DELIVERED status");
        }
        if (orderStatus == OrderStatus.CANCELLED) {
            Assert.assertEquals(deliveryStatus, DeliveryStatus.SCHEDULED,
                    "Cancelled orders should have SCHEDULED delivery status");
        }
    }
}