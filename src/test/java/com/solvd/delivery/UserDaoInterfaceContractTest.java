package com.solvd.delivery;

import com.solvd.delivery.dao.interfaces.IUserDAO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class UserDaoInterfaceContractTest {

    @Test
    public void userDaoHasCrudLikeMethods() throws Exception {
        Class<IUserDAO> clazz = IUserDAO.class;
        String[] expected = {"create", "update", "delete", "getById"};
        for (String name : expected) {
            Method[] methods = clazz.getDeclaredMethods();
            boolean found = false;
            for (Method m : methods) {
                if (m.getName().equals(name)) { found = true; break; }
            }
            Assert.assertTrue(found, "Expected method not found: " + name);
        }
    }
}
