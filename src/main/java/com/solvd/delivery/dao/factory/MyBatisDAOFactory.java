package com.solvd.delivery.dao.factory;

import com.solvd.delivery.dao.impl.mybatis.mapper.UserMyBatisDAO;
import com.solvd.delivery.dao.interfaces.ICustomerDAO;
import com.solvd.delivery.dao.interfaces.IUserMyBatisDAO;

class MyBatisDAOFactory extends DAOFactory {
    @Override
    public ICustomerDAO getCustomerDAO() {
        return null;
    }

    @Override
    public IUserMyBatisDAO getUserDAO() {
        return new UserMyBatisDAO();
    }
}