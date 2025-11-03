package com.solvd.delivery.dao.factory;

import com.solvd.delivery.dao.interfaces.ICustomerDAO;
import com.solvd.delivery.dao.interfaces.IUserMyBatisDAO;

public abstract class DAOFactory {
    public abstract ICustomerDAO getCustomerDAO();
    public abstract IUserMyBatisDAO getUserDAO();

    public static DAOFactory getFactory(String type) {
        return switch (type.toLowerCase()) {
            case "mysql" -> new MySQLDAOFactory();
            case "mybatis" -> new MyBatisDAOFactory();
            default -> throw new IllegalArgumentException("Unknown DAO factory type: " + type);
        };
    }


}


