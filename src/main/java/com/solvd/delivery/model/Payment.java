package com.solvd.delivery.model;

import java.sql.Timestamp;

public class Payment {
    private Long id;
    private Long orderId;
    private double amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private String provider;       // only for digital payments
    private Boolean cashReceived;  // only for cash payments
    private Long customerId;
    private Long courierId;        // nullable
    private Timestamp createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public Boolean getCashReceived() { return cashReceived; }
    public void setCashReceived(Boolean cashReceived) { this.cashReceived = cashReceived; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getCourierId() { return courierId; }
    public void setCourierId(Long courierId) { this.courierId = courierId; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", method=" + method +
                ", status=" + status +
                ", provider='" + provider + '\'' +
                ", cashReceived=" + cashReceived +
                ", customerId=" + customerId +
                ", courierId=" + courierId +
                ", createdAt=" + createdAt +
                '}';
    }
}