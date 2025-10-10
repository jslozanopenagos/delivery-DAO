package com.solvd.delivery.service.impl;

import com.solvd.delivery.model.Order;
import com.solvd.delivery.util.OrderSaxParser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrderService {

    private final OrderSaxParser orderParser;
    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);

    public OrderService() {
        this.orderParser = new OrderSaxParser();
    }

    public List<Order> loadOrdersFromXml(String filePath) {
        LOGGER.info("Loading orders from XML: {}", filePath);
        List<Order> orders = orderParser.parseOrders(filePath);
        LOGGER.info("Loaded {} orders from XML.", orders.size());
        return orders;
    }

    public void saveOrdersToXml(List<Order> orders, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("<orders>\n");
            for (Order order : orders) {
                writer.write("  <order>\n");
                writer.write("    <id>" + order.getId() + "</id>\n");
                writer.write("    <customer_id>" + order.getCustomerId() + "</customer_id>\n");
                writer.write("    <courier_id>" + (order.getCourierId() != null ? order.getCourierId() : "") + "</courier_id>\n");
                writer.write("    <establishment_id>" + order.getEstablishmentId() + "</establishment_id>\n");
                writer.write("    <order_status>" + order.getOrderStatus() + "</order_status>\n");
                writer.write("    <delivery_status>" + order.getDeliveryStatus() + "</delivery_status>\n");
                writer.write("    <delivery_address>" + order.getDeliveryAddress() + "</delivery_address>\n");
                writer.write("    <total_price>" + order.getTotalPrice() + "</total_price>\n");
                writer.write("    <delivery_time>" + (order.getDeliveryTime() != null ? order.getDeliveryTime() : "") + "</delivery_time>\n");
                writer.write("    <created_at>" + order.getCreatedAt() + "</created_at>\n");
                writer.write("  </order>\n");
            }
            writer.write("</orders>");
            LOGGER.info("Saved {}orders to XML: ", filePath);
        } catch (IOException e) {
            LOGGER.error("Error saving orders to XML: {}",e.getMessage());
        }
    }
}
