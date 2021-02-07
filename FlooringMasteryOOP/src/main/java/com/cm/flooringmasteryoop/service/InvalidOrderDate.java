
package com.cm.flooringmasteryoop.service;

/**
 *
 * @author chris
 */
public class InvalidOrderDate extends Exception {

    public InvalidOrderDate(String message) {
        super(message);
    }

    public InvalidOrderDate(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
