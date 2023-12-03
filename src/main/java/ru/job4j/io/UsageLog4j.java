package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4L;
        float f = 5.1F;
        double d = 6.2;
        char c = 'S';
        boolean bl = true;
        LOG.debug("byte = {}", b);
        LOG.debug("short = {}", s);
        LOG.debug("int = {}", i);
        LOG.debug("long = {}", l);
        LOG.debug("float = {}", f);
        LOG.debug("double = {}", d);
        LOG.debug("char = {}", c);
        LOG.debug("boolean = {}", bl);
    }
}
