package com.solvd.delivery.service.interfaces;

import com.solvd.delivery.model.Manager;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IManagerService {
    Optional<Manager> getManagerById(Long id) throws SQLException;
    Optional<Manager> getManagerByUsername(String username) throws SQLException;
    List<Manager> getAllManagers() throws SQLException;
    Long createManager(Manager manager) throws SQLException;
    boolean updateManager(Manager manager) throws SQLException;
    boolean deleteManager(Long managerId) throws SQLException;

    boolean verifyManager(Long managerId) throws SQLException;
}
