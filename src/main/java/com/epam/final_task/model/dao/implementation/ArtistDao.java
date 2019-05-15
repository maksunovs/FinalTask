package com.epam.final_task.model.dao.implementation;

import com.epam.final_task.builder.Builder;
import com.epam.final_task.model.dao.AbstractDao;
import com.epam.final_task.model.entity.Artist;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ArtistDao extends AbstractDao<Artist> {
    private static final String FIND_ALL = "SELECT *FROM artists;";
    private static final String FIND_BY_ID = "SELECT *FROM artists WHERE id=?;";
    private static final String TABLE_NAME = "artists";

    public ArtistDao(Connection connection, Builder<Artist> builder) {
        super(connection, builder);
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
