package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.User;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public interface IUserDAO {
    Optional<User> findById(Long id) throws SQLException;
    Optional<User> findByUsername(String username) throws SQLException;
    List<User> findAll() throws SQLException;
    Long create(User user) throws SQLException;
    boolean update(User user) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
