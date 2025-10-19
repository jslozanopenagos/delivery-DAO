package com.solvd.delivery.service.impl;

import com.solvd.delivery.model.Promotion;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.io.IOException;
import java.util.Collections;

public class PromotionService {

    private static final Logger LOGGER = LogManager.getLogger(PromotionService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Promotion> loadPromotions(String jsonPath) {
        try {
            List<Promotion> promotions = mapper.readValue(new File(jsonPath), new TypeReference<>() {});
            LOGGER.info("Loaded {} promotions from JSON.", promotions.size());
            return promotions;
        } catch (IOException e) {
            LOGGER.error("Error reading promotions JSON: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public void savePromotions(List<Promotion> promotions, String outputPath) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), promotions);
            LOGGER.info("Saved {} promotions to JSON: {}", promotions.size(), outputPath);
        } catch (IOException e) {
            LOGGER.error("Error writing promotions JSON: {}", e.getMessage(), e);
        }
    }
}