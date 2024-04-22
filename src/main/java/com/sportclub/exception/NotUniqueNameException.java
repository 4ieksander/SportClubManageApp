package com.sportclub.exception;


public class NotUniqueNameException extends Exception {
    public NotUniqueNameException(String nazwa) {
        super("Nazwa działu '" + nazwa + "' jest już używana.");
    }
}