package com.solvd.delivery;

import com.solvd.delivery.dao.factory.DAOFactory;
import com.solvd.delivery.dao.factory.MyBatisDAOFactory;
import com.solvd.delivery.dao.factory.MySQLDAOFactory;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DaoFactoryTest {

    @Test
    public void verifyDaoFactoryTypesResolvableTest() {
        DAOFactory mysql = new MySQLDAOFactory();
        DAOFactory mybatis = new MyBatisDAOFactory();
        Assert.assertNotNull(mysql);
        Assert.assertNotNull(mybatis);
    }
}
