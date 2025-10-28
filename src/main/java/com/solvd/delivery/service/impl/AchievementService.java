package com.solvd.delivery.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.solvd.delivery.model.Achievement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.io.IOException;
import java.util.Collections;


public class AchievementService {

    private static final Logger LOGGER = LogManager.getLogger(AchievementService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Achievement> loadAchievements(String jsonPath) {
        try {
            List<Achievement> achievements = mapper.readValue(new File(jsonPath), new TypeReference<>() {});
            LOGGER.info("Loaded {} achievements from JSON.", achievements.size());
            return achievements;
        } catch (IOException e) {
            LOGGER.error("Error reading achievements JSON: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public void saveAchievements(List<Achievement> achievements, String outputPath) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), achievements);
            LOGGER.info("Saved {} achievements to JSON: {}", achievements.size(), outputPath);
        } catch (IOException e) {
            LOGGER.error("Error writing achievements JSON: {}", e.getMessage(), e);
        }
    }
}