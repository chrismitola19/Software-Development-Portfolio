
package com.cm.mp3library.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;



/**
 *
 * @author chris
 */
public class MP3 {
    
    //declare variables
    private String title;
    private  LocalDate releaseDate; //converting to LocalDate 
    private String album;
    private String artist;
    private String genre;
    private String userRating;
  
   

    public MP3(String title){
        this.title = title;
    }


//add getters and setters 

    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public LocalDate getReleaseDate() {
         return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
     

}
