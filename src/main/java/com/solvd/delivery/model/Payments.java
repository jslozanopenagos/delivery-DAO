package com.solvd.delivery.model;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "payments")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payments {

    @XmlElement(name = "payment")
    private List<Payment> payments;

    public Payments() {}

    public Payments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}