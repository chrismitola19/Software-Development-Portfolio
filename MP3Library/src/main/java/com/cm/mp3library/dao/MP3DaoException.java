
package com.cm.mp3library.dao;

/**
 *
 * @author chris
 */
public class MP3DaoException extends Exception{
    
    public MP3DaoException(String message){
        super(message);
    }
    
    public MP3DaoException(String message, Throwable cause){
        super(message, cause);
    }
    //we want MP3DaoException to act just like Exception
    //extending Exception allows us to translate and/or wrap any implementation-specific exception that can be thrown
}
