package com.solvd.delivery.dao.impl.mysql;

import com.solvd.delivery.util.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDAO {

    protected Connection getConnection() throws SQLException {
        return ConnectionPool.getCp().getConnection();
    }

    protected void releaseConnection() {
        try {
            ConnectionPool.getCp().releaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}