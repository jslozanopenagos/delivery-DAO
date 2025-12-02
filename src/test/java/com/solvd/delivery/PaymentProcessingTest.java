package com.solvd.delivery;

import com.solvd.delivery.model.Payment;
import com.solvd.delivery.model.PaymentMethod;
import com.solvd.delivery.model.PaymentStatus;

import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Timestamp;

public class PaymentProcessingTest {

    @DataProvider(name = "paymentMethods")
    public Object[][] paymentMethods() {
        return new Object[][] {
                { PaymentMethod.CREDIT_CARD, "Visa", false },
                { PaymentMethod.DEBIT_CARD, "Mastercard", false },
                { PaymentMethod.CASH, null, true },
                { PaymentMethod.PAYPAL, "PayPal", false },
                { PaymentMethod.DIGITAL_WALLET, "Apple Pay", false },
                { PaymentMethod.DIGITAL_WALLET, "Google Pay", false }
        };
    }

    @Test(dataProvider = "paymentMethods",
            priority = 1,
            groups = { "payment", "smoke"
            },
            description = "Verify payment method validation for all supported payment types")
    public void testPaymentMethodValidation(PaymentMethod method, String provider, boolean isCash) {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setOrderId(100L);
        payment.setAmount(45.99);
        payment.setMethod(method);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setProvider(provider);
        payment.setCustomerId(200L);
        payment.setCourierId(300L);
        payment.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        Assert.assertNotNull(payment.getMethod(), "Payment method should not be null");
        Assert.assertEquals(payment.getMethod(), method,
                "Payment method should match assigned value");
        Assert.assertTrue(payment.getAmount() > 0, "Payment amount should be positive");
        Assert.assertEquals(payment.getStatus(), PaymentStatus.PENDING,
                "Initial payment status should be PENDING");

        if (isCash) {
            payment.setCashReceived(true);
            Assert.assertTrue(payment.getCashReceived(),
                    "Cash payment should have cashReceived flag set");
            Assert.assertNull(payment.getProvider(),
                    "Cash payments should not have a provider");
        } else {
            payment.setCashReceived(false);
            Assert.assertFalse(payment.getCashReceived(),
                    "Non-cash payment should not have cashReceived flag");
            Assert.assertNotNull(payment.getProvider(),
                    "Non-cash payments should have a provider: " + method);
        }

        payment.setStatus(PaymentStatus.COMPLETED);
        Assert.assertEquals(payment.getStatus(), PaymentStatus.COMPLETED,
                "Payment status should be updatable to COMPLETED");
    }
}