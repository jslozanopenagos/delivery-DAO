package com.solvd.delivery.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IBaseUserDAO<T> {
    Optional<T> findById(Long id) throws SQLException;
    Optional<T> findByUsername(String username) throws SQLException;
    List<T> findAll() throws SQLException;
    Long create(T entity) throws SQLException;
    boolean update(T entity) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
