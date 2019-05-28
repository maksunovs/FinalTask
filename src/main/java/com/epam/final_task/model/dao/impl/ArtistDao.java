package com.epam.final_task.model.dao.impl;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.dao.exception.DaoException;
import com.epam.final_task.model.entity.Artist;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class ArtistDao extends AbstractDao<Artist> {
    private static final String FIND_BY_NAME = "SELECT *FROM artists WHERE name=?;";
    private static final String TABLE_NAME = "artists";

    public ArtistDao(Connection connection, Builder<Artist> builder) {
        super(connection, builder);
    }

    public Optional<Artist> findByName(String name) throws DaoException {
        return executeQueryAsSingleResult(FIND_BY_NAME,name);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParameters(Artist artist) {
        Map<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("name",artist.getName());
        parameters.put("country",artist.getCountry());
        return parameters;
    }
}
