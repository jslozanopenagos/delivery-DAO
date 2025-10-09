package com.solvd.delivery.util;

import com.solvd.delivery.model.Payment;
import com.solvd.delivery.model.PaymentMethod;
import com.solvd.delivery.model.PaymentStatus;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PaymentSaxParser {

    public List<Payment> parsePayments(String filePath) {
        List<Payment> payments = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                Payment payment = null;
                String content = null;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equalsIgnoreCase("payment")) {
                        payment = new Payment();
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    content = new String(ch, start, length).trim();
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    if (payment != null) {
                        switch (qName) {
                            case "payment_id" -> payment.setId(Long.parseLong(content));
                            case "order_id" -> payment.setOrderId(Long.parseLong(content));
                            case "amount" -> payment.setAmount(Double.parseDouble(content));
                            case "payment_method" -> payment.setMethod(PaymentMethod.valueOf(content));
                            case "payment_status" -> payment.setStatus(PaymentStatus.valueOf(content));
                            case "payment_provider" -> payment.setProvider(content);
                            case "cash_received" -> payment.setCashReceived(Boolean.parseBoolean(content));
                            case "customer_id" -> payment.setCustomerId(Long.parseLong(content));
                            case "courier_id" -> payment.setCourierId(content.isEmpty() ? null : Long.parseLong(content));
                            case "created_at" -> payment.setCreatedAt(Timestamp.valueOf(content.replace("T", " ")));
                            case "payment" -> payments.add(payment);
                        }
                    }
                }
            };

            parser.parse(new File(filePath), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }
}
