package com.solvd.delivery;

import com.solvd.delivery.model.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class EnumCoverageTest {

    @DataProvider(name = "enumClasses")
    public Object[][] enumClasses() {
        return new Object[][]{
                {UserRole.class},
                {OrderStatus.class},
                {PaymentMethod.class},
                {PaymentStatus.class},
                {DeliveryStatus.class},
                {VehicleType.class}
        };
    }

    @Test(dataProvider = "enumClasses")
    public void enumsHaveValues(Class<? extends Enum<?>> enumClazz) {
        assertNotNull(enumClazz.getEnumConstants());
    }
}
