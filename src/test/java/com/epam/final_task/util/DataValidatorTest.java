package com.epam.final_task.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataValidatorTest {

    private static final DataValidator VALIDATOR = new DataValidator();

    @Test
    public void shouldValidateValue() {
        assertTrue(VALIDATOR.validateValue("1000"));
        assertFalse(VALIDATOR.validateValue("-1000"));
        assertFalse(VALIDATOR.validateValue("-"));
        assertFalse(VALIDATOR.validateValue("19999999999"));
        assertFalse(VALIDATOR.validateValue("199."));
        assertFalse(VALIDATOR.validateValue("199.000"));
        assertTrue(VALIDATOR.validateValue("199.00"));
        assertTrue(VALIDATOR.validateValue("199.0"));
        assertFalse(VALIDATOR.validateValue("199."));
        assertFalse(VALIDATOR.validateValue("dfg"));
    }

    @Test
    public void shouldValidateCountryName() {
        assertTrue(VALIDATOR.validateCountyName("USA"));
        assertTrue(VALIDATOR.validateCountyName("Belarus"));
        assertFalse(VALIDATOR.validateCountyName(""));
        assertFalse(VALIDATOR.validateCountyName("9"));
        assertFalse(VALIDATOR.validateCountyName("d"));
    }

    @Test
    public void shouldValidateInputText() {
        assertFalse(VALIDATOR.validateInputText(""));
        assertTrue(VALIDATOR.validateInputText("fgdg"));
        assertFalse(VALIDATOR.validateInputText("f"));
        assertTrue(VALIDATOR.validateInputText("909sd0d9f"));
        assertFalse(VALIDATOR.validateInputText("9-)(_"));
        assertTrue(VALIDATOR.validateInputText("dgf"));
    }
}
