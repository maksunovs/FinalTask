package com.epam.final_task.model.dao.helper;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DaoHelperTest {
    private static  Map<String,Object> parameters = new LinkedHashMap<>();
    private static final String TABLE_NAME = "test_table";

    private static final String FIRST_COLUMN = "first_name";
    private static final String SECOND_COLUMN = "second_name";

    private static final String EXPECTED = "INSERT INTO test_table (first_name,second_name) VALUES (?,?);";
    private static final DaoHelper HELPER = new DaoHelper();
    @Before
    public void init() {
        parameters.put(FIRST_COLUMN,null);
        parameters.put(SECOND_COLUMN,null);
    }

    @Test
    public void shouldMakeInsertStatement() {
        String actual = HELPER.makeInsertQuery(parameters,TABLE_NAME);
        assertEquals(EXPECTED,actual);
    }
}