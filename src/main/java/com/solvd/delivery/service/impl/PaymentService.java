package com.solvd.delivery.service.impl;

import com.solvd.delivery.model.Payment;
import com.solvd.delivery.model.Payments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class PaymentService {

    private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);
    private final String classpathResource = "payments.xml";

    public List<Payment> loadPaymentsFromClasspath() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(classpathResource);
        if (is == null) {
            LOGGER.error("Resource not found on classpath: {}", classpathResource);
            return Collections.emptyList();
        }

        try (InputStream in = is) {
            JAXBContext jc = JAXBContext.newInstance(Payments.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Payments wrapper = (Payments) unmarshaller.unmarshal(in);
            List<Payment> payments = wrapper.getPayments();
            LOGGER.info("Loaded {} payments from XML.", payments == null ? 0 : payments.size());
            return payments == null ? Collections.emptyList() : payments;
        } catch (JAXBException | IOException e) {
            LOGGER.error("Error loading payments JAXB: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public List<Payment> loadPaymentsWithValidation(String xmlPath, String xsdPath) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Payments.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            unmarshaller.setSchema(sf.newSchema(new File(xsdPath)));
            Payments wrapper = (Payments) unmarshaller.unmarshal(new File(xmlPath));
            return wrapper.getPayments();
        } catch (Exception e) {
            LOGGER.error("Payments XML validation/unmarshalling failed: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public void savePaymentsToFile(List<Payment> payments, String outputPath) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Payments.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            Payments wrapper = new Payments(payments);
            File out = new File(outputPath);
            out.getParentFile().mkdirs();
            try (FileOutputStream fos = new FileOutputStream(out)) {
                marshaller.marshal(wrapper, fos);
            }

            LOGGER.info("Saved {} payments to {}", payments == null ? 0 : payments.size(), out.getAbsolutePath());
        } catch (JAXBException | IOException e) {
            LOGGER.error("Error saving payments to XML: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}