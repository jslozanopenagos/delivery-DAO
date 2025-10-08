package com.solvd.delivery.service.impl;

import com.solvd.delivery.dao.impl.mysql.ManagerDAO;
import com.solvd.delivery.model.Manager;
import com.solvd.delivery.service.interfaces.IManagerService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ManagerService implements IManagerService {
    private final ManagerDAO managerDAO = new ManagerDAO();

    @Override
    public Optional<Manager> getManagerById(Long id) throws SQLException {
        return managerDAO.findById(id);
    }

    @Override
    public Optional<Manager> getManagerByUsername(String username) throws SQLException {
        return managerDAO.findByUsername(username);
    }

    @Override
    public List<Manager> getAllManagers() throws SQLException {
        return managerDAO.findAll();
    }

    @Override
    public Long createManager(Manager manager) throws SQLException {
        return managerDAO.create(manager);
    }

    @Override
    public boolean updateManager(Manager manager) throws SQLException {
        return managerDAO.update(manager);
    }

    @Override
    public boolean deleteManager(Long managerId) throws SQLException {
        return managerDAO.delete(managerId);
    }

    @Override
    public boolean verifyManager(Long managerId) throws SQLException {
        Optional<Manager> optManager = managerDAO.findById(managerId);
        if (optManager.isPresent()) {
            Manager manager = optManager.get();
            manager.setVerified(true);
            return managerDAO.update(manager);
        }
        return false;
    }
}
