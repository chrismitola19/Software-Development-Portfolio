
package com.cm.mp3library.ui;

import com.cm.mp3library.dto.MP3;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author chris
 */
public class MP3View {
    private UserIO io;
    
     public String printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. Add a MP3");
        io.print("2. Remove a MP3");
        io.print("3. Edit a Mp3");
        io.print("4. List All MP3s");
        io.print("5. Find MP3");
        io.print("6. Exit");
        
        return io.readString("Please select the operation you wish to perform.");
    }
    /*All we have done is move the functionality for printing the menu and getting the user's 
selection from the controller over to the view. Notice that we're using composition here — the 
ClassRosterView has-a UserIO member and it uses UserIO to interact with the user. Remember that UserIO is an interface.*/

//We need to get the information we need from the user to create a new Address object, add this method.
    public MP3 getNewMP3Info(){
        String title = io.readString("Title: ");
        String releaseDate = io.readString("Release Date: ");
        String album = io.readString("Album: ");
        String artist = io.readString("Artist: ");
        String genre = io.readString("Genre: ");
        String userRating = io.readString("User Rating/Note: ");
        
        
        
        MP3 currentMP3 = new MP3(title);
        if ((title != "")||(releaseDate != "")||(album != "")||(artist != "")||(genre != "")||(userRating != "")){

        currentMP3.setReleaseDate(LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        currentMP3.setAlbum(album);
        currentMP3.setArtist(artist);
        currentMP3.setGenre(genre);
        currentMP3.setUserRating(userRating);
    }
        return currentMP3;
        /*This method prompts the user for title, release date, album, artist, genre, user rating, and
         gathers this information, creates a new MP3 object, and returns it to the caller.*/
    }
   
    
    public void displayAddMP3Banner(){
        io.print("=== Add MP3 ===");
    }
    public void displayCreateSuccessBanner(){
        io.readString("\nMP3 Successfully Added. Press enter to go to continue.");
       
    }
    //takes a list of address objects as a parameter and displays the info for each address to the screen
    public void displayMP3List(List<MP3> mp3List){
        for (MP3 currentMP3 : mp3List){
            io.print(currentMP3.getTitle()); //just display the order number 
            io.print("");//create a space
        }
        io.readString("Press enter to go to continue."); 
    }
    //add the method to show the Display All Addresses banner
    public void displayDisplayAllBanner(){
        io.print("=== Display All MP3s ===");
    }
    
    public void displayDisplayMP3Banner(){
        io.print("=== Display MP3 ===");   //shows the Display MP3 banner
    }
    public String getTitleChoice(){
        return io.readString("Please enter a title."); //asks the user for the title of the MP3 he/she wishes to display   
        
    }
    public void displayMP3(MP3 mp3){
        if (mp3 != null){  //displays MP3 information to user
            io.print("");
            io.print(mp3.getTitle());
            io.print(mp3.getReleaseDate().toString()); //converts to String
            io.print(mp3.getAlbum());
            io.print(mp3.getArtist());
            io.print(mp3.getGenre());
            io.print(mp3.getUserRating());
            io.print(""); //creates a space  
        } else {
            io.print("Not an MP3");
        }
        io.readString("Please hit enter to continue.");
            
        
    }
    
    public void displayRemoveMP3Banner(){
        io.print("=== Remove MP3 ===");
    }
    public void displayRemoveSuccessBanner(){
        io.readString("\nMP3 was successfully removed. Press enter to continue.");
    }

    public void displayExitBanner(){
        io.print("Goodbye.");
    }
    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }

    //contructor that initializes the io member
    public MP3View(UserIO io){
        this.io = io;
    }
    public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
}
   //edit MP3 
        public boolean edMP3(MP3 edMP3) {
        String userSelection = "";
        if (edMP3 != null) {
            io.print("Edit Menu");
            io.print("[1] Title");
            io.print("[2] Release Date");
            io.print("[3] Album");
            io.print("[4] Artist");
            io.print("[5] Genre");
            io.print("[6] User Rating/Note");

            userSelection = io.readString("Which field would you like to edit?");

            switch (userSelection) {
                case "1":
                    String title = io.readString("Please enter new Title");
                    edMP3.setTitle(title);
                    break;
                case "2":
                    String releaseDate = io.readString("Please enter new Release Date");
                    edMP3.setReleaseDate(LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                    break;
                case "3":
                    String album = io.readString("Please enter new Album");
                    edMP3.setAlbum(album);
                    break;
                case "4":
                    String artist = io.readString("Please enter new Artist");
                    edMP3.setArtist(artist);
                    break;
                case "5":
                    String genre = io.readString("Please enter new Genre");
                    edMP3.setGenre(genre);
                    break;
                case "6":
                    String userNote = io.readString("Please enter new User Rating/Note");
                    edMP3.setUserRating(userNote);
                    break;
                default:
                    io.print("Unknown Command"); //if number is not 1-6
            }
        } else {
            io.print("MP3 not in library"); //if MP3 is not already in the library
            return false;
        }
        return true;
    }

   
    
    public void displayEditMP3Banner(){
        io.print("=== Edit MP3 ===");       
    }
    
    
   
    public void displayNewInfoMP3Banner(){
        io.print("Enter new MP3 information: ");
    }
    
    public void displayContinueBanner(){
        io.readString("Press enter to continue.");
    }
    
    
   
   
}
