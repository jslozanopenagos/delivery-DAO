package com.solvd.delivery;

import com.solvd.delivery.model.Courier;
import com.solvd.delivery.model.UserRole;
import com.solvd.delivery.model.VehicleType;

import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CourierAvailabilityTest {

        private static final Logger LOGGER = LogManager.getLogger(CourierAvailabilityTest.class);

        @Parameters({ "courierName", "licensePlate" })
        @Test(invocationCount = 3,
                groups = {
                "courier", "availability"
                },
                description = "Verify courier availability toggle consistency across multiple invocations")
        public void verifyCourierAvailabilityToggleTest(
                        @Optional("default_courier") String courierName,
                        @Optional("ABC-1234") String licensePlate
        ){
                Courier courier = new Courier(
                                1L,
                                courierName,
                                "password123",
                                "courier@example.com",
                                UserRole.COURIER,
                                new Timestamp(System.currentTimeMillis()),
                                VehicleType.SCOOTER,
                                licensePlate,
                                true);

                Assert.assertTrue(courier.isAvailable(), "Courier should initially be available");

                courier.setAvailable(false);
                Assert.assertFalse(courier.isAvailable(), "Courier should be unavailable after toggle");

                courier.setAvailable(true);
                Assert.assertTrue(courier.isAvailable(), "Courier should be available again");

                LOGGER.info("Courier availability test completed for: {}", courierName);
        }

        @DataProvider(name = "vehicleTypes")
        public Object[][] vehicleTypes() {
                return new Object[][] {
                                { VehicleType.WALKING, "N-A" },
                                { VehicleType.BIKE, "BIKE-002" },
                                { VehicleType.CAR, "CAR-003" },
                                { VehicleType.SCOOTER, "SCO-004" },
                                { VehicleType.STEP_VAN, "VAN-005" }
                };
        }

        @Test(dataProvider = "vehicleTypes",
                groups = {
                "courier", "vehicle"
                },
                priority = 1,
                description = "Verify courier vehicle type assignment for all vehicle types")
        public void testCourierVehicleTypeAssignment(VehicleType vehicleType, String licensePlate) {
                Courier courier = new Courier();
                courier.setId(1L);
                courier.setName("test_courier");
                courier.setEmail("test@courier.com");
                courier.setRole(UserRole.COURIER);
                courier.setVehicleType(vehicleType);
                courier.setLicensePlate(licensePlate);
                courier.setAvailable(true);

                Assert.assertNotNull(courier.getVehicleType(), "Vehicle type should not be null");
                Assert.assertEquals(courier.getVehicleType(), vehicleType,
                                "Vehicle type should match assigned value");
                Assert.assertNotNull(courier.getLicensePlate(), "License plate should not be null");
                Assert.assertEquals(courier.getLicensePlate(), licensePlate,
                                "License plate should match assigned value");
                Assert.assertTrue(courier.getLicensePlate().contains("-"),
                                "License plate should contain hyphen separator");
        }
}
