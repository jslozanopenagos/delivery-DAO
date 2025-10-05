package com.solvd.delivery.dao.interfaces;

import com.solvd.delivery.model.FoodEstablishment;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public interface IFoodEstablishmentDAO {
    Optional<FoodEstablishment> findById(Long id) throws SQLException;
    List<FoodEstablishment> findAll() throws SQLException;
    Long create(FoodEstablishment fe) throws SQLException;
    boolean update(FoodEstablishment fe) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
