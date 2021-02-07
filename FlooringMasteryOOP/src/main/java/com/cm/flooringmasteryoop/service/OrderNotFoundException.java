package com.cm.flooringmasteryoop.service;

/**
 *
 * @author chris
 */
public class OrderNotFoundException extends Exception{

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    
    
}
