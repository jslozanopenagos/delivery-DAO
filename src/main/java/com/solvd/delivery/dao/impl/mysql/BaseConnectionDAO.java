package com.solvd.delivery.dao.impl.mysql;

import com.solvd.delivery.util.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseConnectionDAO {

    protected Connection getConnection() throws SQLException {
        return ConnectionPool.getCp().getConnection();
    }

    protected void releaseConnection(Connection conn) throws SQLException {
        ConnectionPool.getCp().releaseConnection(conn);
    }
}