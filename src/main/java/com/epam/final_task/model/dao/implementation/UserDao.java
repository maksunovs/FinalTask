package com.epam.final_task.model.dao.implementation;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.User;
import com.epam.final_task.util.Hasher;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDao extends AbstractDao<User> {
    private static final String TABLE_NAME = "users";
    private static final String FIND_ALL = "SELECT * FROM users LEFT JOIN cashes " +
            "ON users.id=cashes.user_id;";
    private static final String FIND_BY_ID = "select *from user where id=(?)";
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users LEFT JOIN cashes " +
            "ON users.id=cashes.user_id WHERE users.login=? AND password=?;";
    private static final String UPDATE_CLIENT_CASH = "UPDATE cashes SET value=? WHERE user_id=?;";
    private static final String FIND_CLIENT_CASH = "SELECT *FROM cashes WHERE user_id=?;";
    private final Hasher hasher;
    public UserDao(Connection connection, Builder<User> builder, Hasher hasher) {
        super(connection, builder);
        this.hasher=hasher;
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        String hash = hasher.hash(password);
        return executeQueryAsSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, hash);
    }

    public void updateClientCash(BigDecimal cash, int clientId) throws DaoException {
        executeUpdate(UPDATE_CLIENT_CASH, cash, clientId);
    }



    @Override
    protected Map<String, Object> getParameters(User user) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("login", user.getLogin());
        parameters.put("password", user.getPassword());
        parameters.put("name", user.getName());
        parameters.put("surname", user.getSurname());
        return parameters;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
