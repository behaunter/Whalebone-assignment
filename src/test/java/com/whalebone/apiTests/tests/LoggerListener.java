package com.whalebone.apiTests.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerListener {


    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LoggerListener.class);
        logger.error("Вот тут произошла ошибка");
        logger.info("Информационное поле");


    }
}
