package com.project.accomatch.LoggerPack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerClass {

    private static final Logger logger = LoggerFactory.getLogger(LoggerClass.class);

    public static Logger getLogger(){
        return logger;
    }
}
