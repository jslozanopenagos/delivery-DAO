package com.solvd.delivery.service.impl;

import com.solvd.delivery.model.Payment;
import com.solvd.delivery.util.PaymentSaxParser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PaymentService {

    private final PaymentSaxParser paymentParser;
    private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);

    public PaymentService() {
        this.paymentParser = new PaymentSaxParser();
    }

    public List<Payment> loadPaymentsFromXml(String filePath) {
        LOGGER.info("Loading payments from XML: {}",filePath);
        List<Payment> payments = paymentParser.parsePayments(filePath);
        LOGGER.info("Loaded {} payments from XML.", payments.size());
        return payments;
    }

    public void savePaymentsToXml(List<Payment> payments, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("<payments>\n");
            for (Payment payment : payments) {
                writer.write("  <payment>\n");
                writer.write("    <id>" + payment.getId() + "</id>\n");
                writer.write("    <amount>" + payment.getAmount() + "</amount>\n");
                writer.write("    <method>" + payment.getMethod() + "</method>\n");
                writer.write("  </payment>\n");
            }
            writer.write("</payments>");
            LOGGER.info("Saved {} payments to XML: {}", payments.size(), filePath);
        } catch (IOException e) {
            LOGGER.error("Error saving payments to XML: {}", e.getMessage());
        }
    }
}
