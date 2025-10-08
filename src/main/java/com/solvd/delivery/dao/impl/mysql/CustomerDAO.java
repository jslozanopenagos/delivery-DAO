package com.solvd.delivery.dao.impl.mysql;

import com.solvd.delivery.model.Customer;
import com.solvd.delivery.model.UserRole;
import com.solvd.delivery.dao.interfaces.ICustomerDAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerDAO extends BaseUserDAO<Customer> implements ICustomerDAO {

    @Override
    protected Customer mapResultSetToEntity(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("user_id"));
        customer.setName(rs.getString("username"));
        customer.setPassword(rs.getString("user_password"));
        customer.setEmail(rs.getString("email"));
        customer.setRole(UserRole.valueOf(rs.getString("role")));
        customer.setCreatedAt(rs.getTimestamp("created_at"));
        customer.setAddress(rs.getString("address"));
        customer.setPhoneNumber(rs.getString("phone_number"));
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) throws SQLException {
        String sql = "SELECT u.user_id, u.username, u.user_password, u.email, u.role, u.created_at, " +
                "c.address, c.phone_number " +
                "FROM users u " +
                "JOIN customers c ON u.user_id = c.customer_id " +
                "WHERE u.user_id = ?";

        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapResultSetToEntity(rs));
                    }
                }
            }
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findByUsername(String username) throws SQLException {
        String sql = "SELECT u.user_id, u.username, u.user_password, u.email, u.role, u.created_at, " +
                "c.address, c.phone_number " +
                "FROM users u " +
                "JOIN customers c ON u.user_id = c.customer_id " +
                "WHERE u.username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToEntity(rs));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT u.user_id, u.username, u.user_password, u.email, u.role, u.created_at, " +
                "c.address, c.phone_number " +
                "FROM users u " +
                "JOIN customers c ON u.user_id = c.customer_id";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(mapResultSetToEntity(rs));
            }
        }
        return customers;
    }

    @Override
    public Long create(Customer customer) throws SQLException {
        String insertUserSql = "INSERT INTO users (username, user_password, email, role, created_at) VALUES (?, ?, ?, ?, ?)";
        String insertCustomerSql = "INSERT INTO customers (customer_id, address, phone_number) VALUES (?, ?, ?)";

        Connection conn = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            try (
                    PreparedStatement userStmt = conn.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement customerStmt = conn.prepareStatement(insertCustomerSql)
            ) {
                userStmt.setString(1, customer.getName());
                userStmt.setString(2, customer.getPassword());
                userStmt.setString(3, customer.getEmail());
                userStmt.setString(4, UserRole.CUSTOMER.name());
                userStmt.setTimestamp(5, customer.getCreatedAt());
                userStmt.executeUpdate();

                ResultSet rs = userStmt.getGeneratedKeys();
                if (rs.next()) {
                    long userId = rs.getLong(1);
                    customer.setId(userId);

                    customerStmt.setLong(1, userId);
                    customerStmt.setString(2, customer.getAddress());
                    customerStmt.setString(3, customer.getPhoneNumber());
                    customerStmt.executeUpdate();

                    conn.commit();
                    return userId;
                } else {
                    conn.rollback();
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        String updateUserSql = "UPDATE users SET username=?, user_password=?, email=? WHERE user_id=?";
        String updateCustomerSql = "UPDATE customers SET address=?, phone_number=? WHERE customer_id=?";

        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            try (
                    PreparedStatement userStmt = conn.prepareStatement(updateUserSql);
                    PreparedStatement customerStmt = conn.prepareStatement(updateCustomerSql)
            ) {
                userStmt.setString(1, customer.getName());
                userStmt.setString(2, customer.getPassword());
                userStmt.setString(3, customer.getEmail());
                userStmt.setLong(4, customer.getId());
                userStmt.executeUpdate();

                customerStmt.setString(1, customer.getAddress());
                customerStmt.setString(2, customer.getPhoneNumber());
                customerStmt.setLong(3, customer.getId());
                int rowsAffected = customerStmt.executeUpdate();

                conn.commit();
                return rowsAffected > 0;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
}