package com.epam.final_task.model.dao.impl;

import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.entity.Role;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.Map;

@Repository
public class RoleDao extends AbstractDao<Role> {

    public RoleDao(Connection connection, Builder<T> builder) {
        super(connection, builder);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected Map<String, Object> getParameters(Role role) {
        return null;
    }

    public Role findByTitle(String title) {
        return new Role();
    }
}
