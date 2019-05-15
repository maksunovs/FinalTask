package com.epam.final_task.model.dao;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.dao.helper.DaoHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T> implements Dao<T> {
    protected Connection connection;
    private Builder<T> builder;
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    public AbstractDao(Connection connection, Builder<T> builder) {
        this.connection = connection;
        this.builder = builder;
    }


    protected abstract String getTableName();

    protected abstract Map<String, Object> getParameters(T t);

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement preparedStatement = prepareStatement(query, params)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Failed to update data", e);
        }
    }

    @Override
    public void save(T t) throws DaoException {
        Map<String, Object> parameters = getParameters(t);
        DaoHelper helper = new DaoHelper();
        String stringStatement = helper.makeInsertQuery(parameters, getTableName());
        try (PreparedStatement preparedStatement = connection.prepareStatement(stringStatement)) {
            int index = 1;
            for (Map.Entry<String, Object> pair : parameters.entrySet()) {
                preparedStatement.setObject(index, pair.getValue());
                index++;
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Saving error", e);
        }
    }


    @Override
    public void removeById(int id) throws DaoException {
        DaoHelper helper = new DaoHelper();
        String deleteQuery = helper.makeDeleteQuery(getTableName());
        try (PreparedStatement preparedStatement = prepareStatement(deleteQuery, id)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Removing error", e);
        }

    }

    public Optional<T> findById(int id) throws DaoException {
        return executeQueryAsSingleResult("SELECT *FROM " + getTableName() + " WHERE id=?;", id);
    }

    public List<T> findAll() throws DaoException {
        return executeQuery("SELECT *FROM " + getTableName() + ";");
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatement(query, params)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                result.add(entity);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Query execution exception", e);
        }
        return result;
    }

    protected Optional<T> executeQueryAsSingleResult(String query, Object... params) throws DaoException {
        List<T> result = executeQuery(query, params);
        if (result.size() == 1) {
            return Optional.of(result.get(0));
        } else {
            return Optional.empty();
        }
    }

    private PreparedStatement prepareStatement(String query, Object... params) throws DaoException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new DaoException("Prepare statement error", e);
        }
    }

}

