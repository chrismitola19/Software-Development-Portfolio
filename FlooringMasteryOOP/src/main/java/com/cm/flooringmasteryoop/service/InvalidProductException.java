
package com.cm.flooringmasteryoop.service;

/**
 *
 * @author chris
 */
public class InvalidProductException extends Exception{

    public InvalidProductException(String message) {
        super(message);
    }

    public InvalidProductException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
