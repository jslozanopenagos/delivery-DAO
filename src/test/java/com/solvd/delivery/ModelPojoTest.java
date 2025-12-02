package com.solvd.delivery;

import com.solvd.delivery.model.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;

public class ModelPojoTest {

    @Test
    public void verifyUserPojoTest() {
        User u = new User();
        u.setId(10L);
        u.setName("Alice");
        u.setEmail("alice@example.com");
        u.setRole(UserRole.CUSTOMER);
        Assert.assertEquals(u.getId(), Long.valueOf(10));
        Assert.assertEquals(u.getName(), "Alice");
        Assert.assertEquals(u.getEmail(), "alice@example.com");
        Assert.assertEquals(u.getRole(), UserRole.CUSTOMER);
    }

    @Test
    public void verifyOrderPojoTest() {
        Order order = new Order();
        order.setId(5L);
        order.setCustomerId(2L);
        order.setCourierId(null);
        order.setDeliveryAddress("Street 123");
        order.setTotalPrice(25.5);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setDeliveryStatus(DeliveryStatus.SCHEDULED);
        Timestamp now = Timestamp.valueOf("2024-10-01 10:00:00");
        order.setCreatedAt(now);
        Assert.assertEquals(order.getId(), Long.valueOf(5));
        Assert.assertEquals(order.getCustomerId(), Long.valueOf(2));
        Assert.assertNull(order.getCourierId());
        Assert.assertEquals(order.getDeliveryAddress(), "Street 123");
        Assert.assertEquals(order.getTotalPrice(), 25.5, 0.0001);
        Assert.assertEquals(order.getOrderStatus(), OrderStatus.PENDING);
        Assert.assertEquals(order.getDeliveryStatus(), DeliveryStatus.SCHEDULED);
        Assert.assertEquals(order.getCreatedAt(), now);
    }
}
