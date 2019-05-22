package com.epam.final_task.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Hasher {

    public String hash(String string) {
        return DigestUtils.sha256Hex(string);
    }
}
