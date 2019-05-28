package com.epam.final_task.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    private static final Pattern VALUE_PATTERN = Pattern.compile("[1-9][0-9]{0,6}(\\.[0-9]{1,2})?");
    private static final Pattern INPUT_TEXT_PATTERN = Pattern.compile("\\s*([0-9]|[A-Za-zА-Яа-яЁё])([0-9]|[A-Za-zА-Яа-яЁё\\-\\./']|\\s){1,90}");
    private static final Pattern COUNTRY_NAME_PATTERN = Pattern.compile("\\s*[A-Za-zА-Яа-яЁё]([A-Za-zА-Яа-яЁё]|\\s){1,90}");

    public Boolean validateCountyName(String country) {
        Matcher matcher = COUNTRY_NAME_PATTERN.matcher(country);
        return matcher.matches();
    }

    public Boolean validateValue(String value) {
        Matcher matcher = VALUE_PATTERN.matcher(value);
        return matcher.matches();
    }

    public Boolean validateInputText(String text) {
        Matcher matcher = INPUT_TEXT_PATTERN.matcher(text);
        return matcher.matches();
    }
}
