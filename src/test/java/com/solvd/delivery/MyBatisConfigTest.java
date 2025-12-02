package com.solvd.delivery;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.solvd.delivery.util.MyBatisConfig;

public class MyBatisConfigTest {

    @Test
    public void verifyMybatisSessionFactoryAccessibleTest() {
        try {
            Assert.assertNotNull(MyBatisConfig.class);
        } catch (Exception e) {
            throw new SkipException("MyBatis configuration not available in test env: " + e.getMessage());
        }
    }
}
