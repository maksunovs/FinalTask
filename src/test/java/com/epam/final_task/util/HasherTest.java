package com.epam.final_task.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class HasherTest {


    private static final Hasher HASHER = new Hasher();
    private static final String SOME_DATA = "123457qwerty";
    private static final String DATA_HEX = "bd4430cb49a77697b703fe67342bbaa7f21744e9255a63fd489e1b0dc083bb24";

    @Test
    public void shouldHashData() {
        String actualHex = HASHER.hash(SOME_DATA);
        assertEquals(DATA_HEX, actualHex);
    }
}