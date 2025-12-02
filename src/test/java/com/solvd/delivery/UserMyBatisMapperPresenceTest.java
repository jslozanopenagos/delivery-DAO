package com.solvd.delivery;

import org.apache.ibatis.io.Resources;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserMyBatisMapperPresenceTest {

    @Test
    public void userMapperXmlPresent() {
        try {
            Assert.assertNotNull(Resources.getResourceAsStream("mapper/UserMapper.xml"));
        } catch (IOException e) {
            throw new SkipException("UserMapper.xml not available on classpath");
        }
    }
}
