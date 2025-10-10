package com.solvd.delivery.model;

import com.solvd.delivery.util.TimeStampAdapter;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.sql.Timestamp;

@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment {
    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "orderId")
    private Long orderId;

    @XmlElement(name = "amount")
    private double amount;

    @XmlElement(name = "method")
    private PaymentMethod method;

    @XmlElement(name = "status")
    private PaymentStatus status;

    @XmlElement(name = "provider")
    private String provider;

    @XmlElement(name = "cashReceived")
    private Boolean cashReceived;

    @XmlElement(name = "customer_id")
    private Long customerId;

    @XmlElement(name = "courier_id")
    private Long courierId;

    @XmlElement(name = "created_at")
    @XmlJavaTypeAdapter(TimeStampAdapter.class)
    private Timestamp createdAt;


    public Payment() {}

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