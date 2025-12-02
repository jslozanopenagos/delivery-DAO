package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface IUserDAO extends IBaseUserDAO<User> {
    Long create(User entity) throws SQLException;
    boolean update(User entity) throws SQLException;
    boolean delete(Long id) throws SQLException;

    default Optional<User> getById(Long id) throws SQLException {
        return findById(id);
    }
}
