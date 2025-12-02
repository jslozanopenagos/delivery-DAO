package com.solvd.delivery;

import org.testng.Assert;
import org.testng.annotations.*;

public class DataProviderDrivenTest {

    @DataProvider(name = "emails")
    public Object[][] emails() {
        return new Object[][]{
                {"alice@example.com", true},
                {"bob@", false},
                {"carol@example.org", true},
                {"bad", false}
        };
    }

    @Factory
    public Object[] factory() {
        return new Object[]{ new DataProviderDrivenTest() };
    }

    @Test(dataProvider = "emails")
    public void verifyEmailPatternTest(String email, boolean expectedValid) {
        boolean valid = email.contains("@") && email.contains(".");
        Assert.assertEquals(valid, expectedValid, "Email validity differs");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void expectedExceptionDemo() {
        throw new IllegalArgumentException("demo");
    }
}
