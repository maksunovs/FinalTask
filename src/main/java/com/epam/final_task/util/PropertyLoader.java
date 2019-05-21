package com.epam.final_task.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    public Properties load(String name) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(name);
        Properties properties = new Properties();
        if (in != null) {
            properties.load(in);
            in.close();
        }
        return properties;

    }
}
