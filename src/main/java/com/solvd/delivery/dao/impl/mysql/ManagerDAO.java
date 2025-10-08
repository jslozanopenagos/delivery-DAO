package com.solvd.delivery.dao.impl.mysql;

import com.solvd.delivery.model.Manager;
import com.solvd.delivery.model.UserRole;
import com.solvd.delivery.dao.interfaces.IManagerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManagerDAO extends BaseUserDAO<Manager> implements IManagerDAO {

    @Override
    protected Manager mapResultSetToEntity(ResultSet rs) throws SQLException {
        Manager manager = new Manager();
        manager.setId(rs.getLong("user_id"));
        manager.setName(rs.getString("username"));
        manager.setPassword(rs.getString("user_password"));
        manager.setEmail(rs.getString("email"));
        manager.setRole(UserRole.valueOf(rs.getString("role")));
        manager.setCreatedAt(rs.getTimestamp("created_at"));
        manager.setVerified(rs.getBoolean("verified"));
        return manager;
    }

    @Override
    public Optional<Manager> findById(Long id) throws SQLException {
        String sql = "SELECT u.user_id, u.username, u.user_password, u.email, u.role, u.created_at, " +
                "m.verified " +
                "FROM users u " +
                "JOIN managers m ON u.user_id = m.manager_id " +
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
    public Optional<Manager> findByUsername(String username) throws SQLException {
        String sql = "SELECT u.user_id, u.username, u.user_password, u.email, u.role, u.created_at, " +
                "m.verified " +
                "FROM users u " +
                "JOIN managers m ON u.user_id = m.manager_id " +
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
    public List<Manager> findAll() throws SQLException {
        List<Manager> managers = new ArrayList<>();
        String sql = "SELECT u.user_id, u.username, u.user_password, u.email, u.role, u.created_at, " +
                "m.verified " +
                "FROM users u " +
                "JOIN managers m ON u.user_id = m.manager_id";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                managers.add(mapResultSetToEntity(rs));
            }
        }
        return managers;
    }

    @Override
    public Long create(Manager manager) throws SQLException {
        String insertUserSql = "INSERT INTO users (username, user_password, email, role, created_at) VALUES (?, ?, ?, ?, ?)";
        String insertManagerSql = "INSERT INTO managers (manager_id, verified) VALUES (?, ?)";

        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            try (
                    PreparedStatement userStmt = conn.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement managerStmt = conn.prepareStatement(insertManagerSql)
            ) {
                userStmt.setString(1, manager.getName());
                userStmt.setString(2, manager.getPassword());
                userStmt.setString(3, manager.getEmail());
                userStmt.setString(4, UserRole.MANAGER.name());
                userStmt.setTimestamp(5, manager.getCreatedAt());
                userStmt.executeUpdate();

                ResultSet rs = userStmt.getGeneratedKeys();
                if (rs.next()) {
                    long userId = rs.getLong(1);
                    manager.setId(userId);

                    managerStmt.setLong(1, userId);
                    managerStmt.setBoolean(2, manager.isVerified());
                    managerStmt.executeUpdate();

                    conn.commit();
                    return userId;
                } else {
                    conn.rollback();
                    throw new SQLException("Creating manager failed, no ID obtained.");
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
    public boolean update(Manager manager) throws SQLException {
        String updateUserSql = "UPDATE users SET username=?, user_password=?, email=? WHERE user_id=?";
        String updateManagerSql = "UPDATE managers SET verified=? WHERE manager_id=?";

        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            try (
                    PreparedStatement userStmt = conn.prepareStatement(updateUserSql);
                    PreparedStatement managerStmt = conn.prepareStatement(updateManagerSql)
            ) {
                userStmt.setString(1, manager.getName());
                userStmt.setString(2, manager.getPassword());
                userStmt.setString(3, manager.getEmail());
                userStmt.setLong(4, manager.getId());
                userStmt.executeUpdate();

                managerStmt.setBoolean(1, manager.isVerified());
                managerStmt.setLong(2, manager.getId());
                int rowsAffected = managerStmt.executeUpdate();

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