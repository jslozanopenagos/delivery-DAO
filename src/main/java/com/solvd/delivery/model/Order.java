package com.solvd.delivery.model;

import com.solvd.delivery.dao.interfaces.IOrderDAO;

import java.sql.Timestamp;

public class Order {
    private Long orderId;
    private Long customerId;
    private Long courierId;
    private Long establishmentId;
    private OrderStatus orderStatus;
    private DeliveryStatus deliveryStatus;
    private String deliveryAddress;
    private double totalPrice;
    private Timestamp deliveryTime;
    private Timestamp createdAt;


    public Order() {}

    public Order(
            Long orderId, Long customerId,
            Long courierId, Long establishmentId,
            OrderStatus orderStatus, DeliveryStatus deliveryStatus,
            String deliveryAddress, double totalPrice,
            Timestamp deliveryTime, Timestamp createdAt
    ) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.courierId = courierId;
        this.establishmentId = establishmentId;
        this.orderStatus = orderStatus;
        this.deliveryStatus = deliveryStatus;
        this.deliveryAddress = deliveryAddress;
        this.totalPrice = totalPrice;
        this.deliveryTime = deliveryTime;
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Long getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(Long establishmentId) {
        this.establishmentId = establishmentId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override public String toString() {
        return "Order{"+orderId+", total="+totalPrice+"}";
    }
}