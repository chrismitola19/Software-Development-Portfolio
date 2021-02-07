
package com.cm.classroster.service;

/**
 *
 * @author chris
 */
public class ClassRosterDuplicateIdException extends Exception {
    
    public ClassRosterDuplicateIdException(String message){
        super(message);
    }

    public ClassRosterDuplicateIdException(String message, Throwable cause){
        super(message, cause);
    }


}

