package com.epam.final_task.model.dao;

import com.epam.final_task.model.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> findAll() throws DaoException;
    Optional<T>findById(int id) throws DaoException;
    void save (T t) throws DaoException;
    void removeById(int id) throws DaoException;

}
