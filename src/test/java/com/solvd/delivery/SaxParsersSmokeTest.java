package com.solvd.delivery;

import com.solvd.delivery.model.Order;
import com.solvd.delivery.model.Payment;
import com.solvd.delivery.util.OrderSaxParser;
import com.solvd.delivery.util.PaymentSaxParser;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SaxParsersSmokeTest {

    private Path ordersXml;
    private Path paymentsXml;

    @BeforeMethod
    public void setup() {
        ordersXml = Path.of("src", "main", "resources", "xml", "orders.xml");
        paymentsXml = Path.of("src", "main", "resources", "xml", "payments.xml");
    }

    @Test
    @Parameters({"parseOrders"})
    public void parseOrdersXml(@Optional("true") boolean parseOrders) {
        if (!parseOrders) throw new SkipException("Skipping by parameter");
        if (!Files.exists(ordersXml)) throw new SkipException("orders.xml not present in workspace");
        OrderSaxParser parser = new OrderSaxParser();
        List<Order> orders = parser.parseOrders(ordersXml.toString());
        Assert.assertNotNull(orders);
        Assert.assertTrue(orders.size() >= 0);
    }

    @Test
    @Parameters({"parsePayments"})
    public void parsePaymentsXml(@Optional("true") boolean parsePayments) {
        if (!parsePayments) throw new SkipException("Skipping by parameter");
        if (!Files.exists(paymentsXml)) throw new SkipException("payments.xml not present in workspace");
        PaymentSaxParser parser = new PaymentSaxParser();
        List<Payment> payments = parser.parsePayments(paymentsXml.toString());
        Assert.assertNotNull(payments);
        Assert.assertTrue(payments.size() >= 0);
    }
}
