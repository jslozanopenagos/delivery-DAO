package com.solvd.delivery.util;

import com.solvd.delivery.model.DeliveryStatus;
import com.solvd.delivery.model.Order;
import com.solvd.delivery.model.OrderStatus;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderSaxParser {

    public List<Order> parseOrders(String filePath) {
        List<Order> orders = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                Order order = null;
                String content = null;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equalsIgnoreCase("order")) {
                        order = new Order();
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    content = new String(ch, start, length).trim();
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    if (order != null) {
                        switch (qName) {
                            case "id" -> order.setId(Long.parseLong(content));
                            case "customer_id" -> order.setCustomerId(Long.parseLong(content));
                            case "courier_id" -> order.setCourierId(content.isEmpty() ? null : Long.parseLong(content));
                            case "establishment_id" -> order.setEstablishmentId(Long.parseLong(content));
                            case "order_status" -> order.setOrderStatus(OrderStatus.valueOf(content));
                            case "delivery_status" -> order.setDeliveryStatus(DeliveryStatus.valueOf(content));
                            case "delivery_address" -> order.setDeliveryAddress(content);
                            case "total_price" -> order.setTotalPrice(Double.parseDouble(content));
                            case "delivery_time" -> order.setDeliveryTime(content.isEmpty() ? null : Timestamp.valueOf(content.replace("T", " ")));
                            case "created_at" -> order.setCreatedAt(Timestamp.valueOf(content.replace("T", " ")));
                            case "order" -> orders.add(order);
                        }
                    }
                }
            };

            parser.parse(new File(filePath), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
}
