package com.epam.final_task.builder;

import com.epam.final_task.model.dao.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Builder<T> {
    T build(ResultSet resultSet) throws DaoException, SQLException;
}
