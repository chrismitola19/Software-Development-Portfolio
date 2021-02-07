
package com.cm.flooringmasteryoop.service;

/**
 *
 * @author chris
 */
public class InvalidAreaException extends Exception{

    public InvalidAreaException(String message) {
        super(message);
    }

    public InvalidAreaException(String message, Throwable cause) {
        super(message, cause);
    }

}
