package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.MenuItem;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public interface IMenuItemDao {
    Optional<MenuItem> findById(Long id) throws SQLException;
    List<MenuItem> findAll() throws SQLException;
    Long create(MenuItem item) throws SQLException;
    boolean update(MenuItem item) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
