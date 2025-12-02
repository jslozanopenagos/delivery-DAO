package com.solvd.delivery.dao.factory;

import com.solvd.delivery.dao.impl.mysql.CustomerDAO;
import com.solvd.delivery.dao.interfaces.ICustomerDAO;
import com.solvd.delivery.dao.interfaces.IUserMyBatisDAO;

public class MySQLDAOFactory extends DAOFactory {
    @Override
    public ICustomerDAO getCustomerDAO() {
        return new CustomerDAO();
    }

    @Override
    public IUserMyBatisDAO getUserDAO() {
        return null;
    }
}