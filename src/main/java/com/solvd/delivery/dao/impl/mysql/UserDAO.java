package com.solvd.delivery.dao.impl.mysql;

import com.solvd.delivery.model.User;
import com.solvd.delivery.model.UserRole;
import com.solvd.delivery.dao.interfaces.IUserDAO;

import java.sql.*;

public class UserDAO extends BaseUserDAO<User> implements IUserDAO {

    @Override
    protected User mapResultSetToEntity(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setName(rs.getString("username"));
        user.setPassword(rs.getString("user_password"));
        user.setEmail(rs.getString("email"));
        user.setRole(UserRole.valueOf(rs.getString("role")));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        return user;
    }

    @Override
    public Long create(User user) throws SQLException {
        String sql = "INSERT INTO users (username, user_password, email, role, created_at) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = getConnection();
            try ( PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getRole().name());
                stmt.setTimestamp(5, user.getCreatedAt());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    user.setId(id);
                    return id;
                }
            }
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
        throw new SQLException("Creating user failed, no ID obtained.");
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = "UPDATE users SET username=?, user_password=?, email=?, role=? WHERE user_id=?";
        Connection conn = null;

        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getRole().name());
                stmt.setLong(5, user.getId());

                return stmt.executeUpdate() > 0;
            }
        } finally {
            if (conn != null) {
                releaseConnection(conn);
            }
        }
    }
}