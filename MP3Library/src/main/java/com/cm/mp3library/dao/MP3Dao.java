
package com.cm.mp3library.dao;

import com.cm.mp3library.dto.MP3;
import java.util.List;

/**
 *
 * @author chris
 */
public interface MP3Dao {
    
    MP3 addMP3(String title, MP3 mp3) //adds given MP3 to roster 
    throws MP3DaoException;
    
    List<MP3> getAllMP3s() 
    throws MP3DaoException;
    //Returns a String array containing the titles
    
    MP3 getMP3(String title)
    throws MP3DaoException;
    //returns the MP3 object with given last name 
    
    MP3 removeMP3(String title)
    throws MP3DaoException;
    //removes MP3 by last name
    
    MP3 edMP3(String title, MP3 mp3)
    throws MP3DaoException;
    
}
