package com.solvd.delivery.dao.impl.mysql;

import com.solvd.delivery.model.Courier;
import com.solvd.delivery.model.UserRole;
import com.solvd.delivery.model.VehicleType;
import com.solvd.delivery.dao.interfaces.ICourierDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourierDAO extends BaseUserDAO<Courier> implements ICourierDAO {

    @Override
    protected Courier mapResultSetToEntity(ResultSet rs) throws SQLException {
        Courier courier = new Courier();
        courier.setId(rs.getLong("user_id"));
        courier.setName(rs.getString("username"));
        courier.setPassword(rs.getString("user_password"));
        courier.setEmail(rs.getString("email"));
        courier.setRole(UserRole.valueOf(rs.getString("role")));
        courier.setCreatedAt(rs.getTimestamp("created_at"));
        courier.setVehicleType(VehicleType.valueOf(rs.getString("vehicle_type")));
        courier.setLicensePlate(rs.getString("license_plate"));
        courier.setAvailability(rs.getBoolean("availability"));
        return courier;
    }

    @Override
    public Optional<Courier> findById(Long id) throws SQLException {
        String sql = "SELECT u.user_id, u.username, u.user_password, u.email, u.role, u.created_at, " +
                "c.vehicle_type, c.license_plate, c.availability " +
                "FROM users u " +
                "JOIN couriers c ON u.user_id = c.courier_id " +
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
    public Optional<Courier> findByUsername(String username) throws SQLException {
        String sql = "SELECT u.user_id, u.username, u.user_password, u.email, u.role, u.created_at, " +
                "c.vehicle_type, c.license_plate, c.availability " +
                "FROM users u " +
                "JOIN couriers c ON u.user_id = c.courier_id " +
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
    public List<Courier> findAll() throws SQLException {
        List<Courier> couriers = new ArrayList<>();
        String sql = "SELECT u.user_id, u.username, u.user_password, u.email, u.role, u.created_at, " +
                "c.vehicle_type, c.license_plate, c.availability " +
                "FROM users u " +
                "JOIN couriers c ON u.user_id = c.courier_id";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                couriers.add(mapResultSetToEntity(rs));
            }
        }
        return couriers;
    }

    @Override
    public Long create(Courier courier) throws SQLException {
        String insertUserSql = "INSERT INTO users (username, user_password, email, role, created_at) VALUES (?, ?, ?, ?, ?)";
        String insertCourierSql = "INSERT INTO couriers (courier_id, vehicle_type, license_plate, availability) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            try (
                    PreparedStatement userStmt = conn.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement courierStmt = conn.prepareStatement(insertCourierSql)
            ) {
                userStmt.setString(1, courier.getName());
                userStmt.setString(2, courier.getPassword());
                userStmt.setString(3, courier.getEmail());
                userStmt.setString(4, UserRole.COURIER.name());
                userStmt.setTimestamp(5, courier.getCreatedAt());
                userStmt.executeUpdate();

                ResultSet rs = userStmt.getGeneratedKeys();
                if (rs.next()) {
                    long userId = rs.getLong(1);
                    courier.setId(userId);

                    courierStmt.setLong(1, userId);
                    courierStmt.setString(2, courier.getVehicleType().name());
                    courierStmt.setString(3, courier.getLicensePlate());
                    courierStmt.setBoolean(4, courier.isAvailability());
                    courierStmt.executeUpdate();

                    conn.commit();
                    return userId;
                } else {
                    conn.rollback();
                    throw new SQLException("Creating courier failed, no ID obtained.");
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
    public boolean update(Courier courier) throws SQLException {
        String updateUserSql = "UPDATE users SET username=?, user_password=?, email=? WHERE user_id=?";
        String updateCourierSql = "UPDATE couriers SET vehicle_type=?, license_plate=?, availability=? WHERE courier_id=?";

        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            try (
                    PreparedStatement userStmt = conn.prepareStatement(updateUserSql);
                    PreparedStatement courierStmt = conn.prepareStatement(updateCourierSql)
            ) {
                userStmt.setString(1, courier.getName());
                userStmt.setString(2, courier.getPassword());
                userStmt.setString(3, courier.getEmail());
                userStmt.setLong(4, courier.getId());
                userStmt.executeUpdate();

                courierStmt.setString(1, courier.getVehicleType().name());
                courierStmt.setString(2, courier.getLicensePlate());
                courierStmt.setBoolean(3, courier.isAvailability());
                courierStmt.setLong(4, courier.getId());
                int rowsAffected = courierStmt.executeUpdate();

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
