
package com.cm.mp3library.dao;

import com.cm.mp3library.dto.MP3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author chris
 */
public class MP3DaoFileImpl implements MP3Dao {
    
    //add two constants 
    public static final String MP3_FILE = "mp3list.txt";
    public static final String DELIMITER = "---";

    
    @Override
    public MP3 addMP3(String title, MP3 mp3) throws MP3DaoException {
    loadRoster();
    MP3 newMP3 = mp3s.put(title, mp3);
    writeRoster();
    //implement the addMP3 method
    return newMP3;
    }

    @Override
    public List<MP3> getAllMP3s() throws MP3DaoException {
    loadRoster();
    return new ArrayList(mp3s.values()); //this code gets all of the MP3 Objects out of the mp3s map as a collection by calling the values() method
    }

    @Override
    public MP3 getMP3(String title) throws MP3DaoException {
    loadRoster();
    return mp3s.get(title);
    }

    @Override
    public MP3 removeMP3(String title) throws MP3DaoException {
    loadRoster();
    MP3 removedMP3 = mp3s.remove(title);
    writeRoster();
    return removedMP3; 
    }
     @Override
    public MP3 edMP3(String title, MP3 mp3) throws MP3DaoException {
       MP3 editedMP3 = mp3s.replace(title, mp3);
       return editedMP3;
    }
   
    
    private Map<String, MP3> mp3s = new HashMap(); //name of HashMap is mp3s
   
    private MP3 unmarshallMP3(String mp3AsText){
    // mp3AsText is expecting a line read in from our file.
    
    //
    // We then split that line on our DELIMITER - which we are using as ---
    // Leaving us with an array of Strings, stored in studentTokens.
    
    String[] mp3Tokens = mp3AsText.split(DELIMITER);
    
    
    // Given the pattern above, the title is in index 0 of the array.
    String title = mp3Tokens[0];

    // Which we can then use to create a new MP3 object to satisfy the requirements of the MP3constructor.
    MP3 mp3FromFile = new MP3(title);

    // However there are 5 remaining tokens that need to be set into the new mp3 object. 
 
    // Index 1 - Release Date
    mp3FromFile.setReleaseDate(LocalDate.parse(mp3Tokens[1])); //takes the string from file and turns it into LocalDate

    // Index 2 - Album
    mp3FromFile.setAlbum(mp3Tokens[2]);

    // Index 3 - Artist
    mp3FromFile.setArtist(mp3Tokens[3]);
    
    //Index 4 - Genre
    mp3FromFile.setGenre(mp3Tokens[4]);
    
    //Index 5 - User Rating
    mp3FromFile.setUserRating(mp3Tokens[5]);

    // We have now created a MP3! Return it!
    return mp3FromFile;
}
    private void loadRoster() throws MP3DaoException {
    Scanner scanner;
    
//we catch the FileNotFoundException and translate it into a MP3DaoException.
    try {
        // Create Scanner for reading the file
        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(MP3_FILE)));
    } catch (FileNotFoundException e) {
        throw new MP3DaoException( "-_- Could not load roster data into memory.", e);
    }
    // currentLine holds the most recent line read from the file
    String currentLine;
    // currentMP3 holds the most recent student unmarshalled
    MP3 currentMP3;
    /* Go through MP3_FILE line by line, decoding each line into a MP3 object by 
    calling the unmarshallMP3 method. Process while we have more lines in the file*/
    while (scanner.hasNextLine()) {
        // get the next line in the file
        currentLine = scanner.nextLine();
        // unmarshall the line into a MP3
        currentMP3 = unmarshallMP3(currentLine);

        // We are going to use the title as the map key for our MP3 object.
        // Put currentMP3 into the map using student id as the key
        mp3s.put(currentMP3.getTitle(), currentMP3);
    }
    // close scanner
    scanner.close();
}
    private String marshallMP3(MP3 aMp3){
    // We need to turn a MP3 object into a line of text for our file.

     //get out each property and concatenate with our DELIMITER as a kind of spacer. 

    // Start with the title, since that's supposed to be first.
    String mp3AsText = aMp3.getTitle() + DELIMITER;

    // Release Date
    mp3AsText += aMp3.getReleaseDate() + DELIMITER;

    // Album
    mp3AsText += aMp3.getAlbum() + DELIMITER;

    // Artist
    mp3AsText += aMp3.getArtist() + DELIMITER;
    
    // Genre
    mp3AsText += aMp3.getGenre() + DELIMITER;
    
    // User Rating
    mp3AsText += aMp3.getUserRating() + " "; //skip DELIMITER here because its the end 
    

    // We have now turned a student to text! Return it!
    return mp3AsText;
}
    /**
 * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
 * for file format.
 * 
 * @throws ClassRosterDaoException if an error occurs writing to the file
 */
private void writeRoster() throws MP3DaoException {
    /* We are not handling the IOException - but
    we are translating it to an application specific exception and 
    then simple throwing it (i.e. 'reporting' it) to the code that
    called us.  It is the responsibility of the calling code to 
    handle any errors that occur.*/
    
    PrintWriter out;

    try {
        out = new PrintWriter(new FileWriter(MP3_FILE));
    } catch (IOException e) {
        throw new MP3DaoException(
                "Could not save MP3 data.", e); //e creates object for exception
    }

    // Write out the MP3 objects to the roster file.
    
    String mp3AsText;
    List<MP3> mp3List = this.getAllMP3s();
    for (MP3 currentMP3 : mp3List) {
        // turn a MP3 into a String
        mp3AsText = marshallMP3(currentMP3);
        // write the MP3 object to the file
        out.println(mp3AsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
}

   


}