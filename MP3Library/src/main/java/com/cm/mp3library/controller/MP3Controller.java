
package com.cm.mp3library.controller;


import com.cm.mp3library.dao.MP3Dao;
import com.cm.mp3library.dao.MP3DaoException;
import com.cm.mp3library.dao.MP3DaoFileImpl;
import com.cm.mp3library.dto.MP3;
import com.cm.mp3library.ui.MP3View;
import com.cm.mp3library.ui.UserIO;
import com.cm.mp3library.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author chris
 */
//"brains of operation", it will control when the menu system is displayed

public class MP3Controller {
    
    MP3View view;
    MP3Dao dao;
    private UserIO io = new UserIOConsoleImpl();
    
    public void run(){
        boolean keepGoing = true;
        String menuSelection = "";  //this will make it so you can enter a number for what you want to do
        //add try catch to react to the MP3DaoExceptions that potentially get thrown at our code
        try {
        while (keepGoing) {
            io.print("Main Menu");
            io.print("1. Add a MP3");
            io.print("2. Remove a MP3");
            io.print("3. Edit a MP3");
            io.print("4. List All MP3s");
            io.print("5. Search for MP3");
            io.print("6. Exit");
            
            menuSelection = io.readString("Please select the operation you wish to perform.");
            
            //add switch
            switch (menuSelection){
                case "1":
                    createMP3(); //make call createMP3 in the run method 
                    break;
                case "2": 
                    removeMP3(); //make call removeMP3 in the run method
                    break;
                case "3":
                   editMP3();
                    break;
                case "4":
                    listMP3s(); //make call to listMP3 in the run method
                    break;
                case "5":
                    findMP3(); //make call to findMP3 in the run method
                    break;
                case "6": 
                    keepGoing = false;   //for exit 
                    break;
                default:
                    unknownCommand();
                      //anything put in other than 1-6
            }//end switch
        }//end while
        exitMessage();
        
        } catch (MP3DaoException e){
            view.displayErrorMessage(e.getMessage());
        }
        }//end run 
    
        private String getMenuSelection(){
            return view.printMenuAndGetSelection();
        }

        //viewMP3 method, asks the view to display the View MP3 banner and gets the title from the user.
        //Then it asks the DAO for the MP3s associated with the title.   
        //finally it asks the view to display the MP3 info
        private void createMP3() throws MP3DaoException{
            view.displayAddMP3Banner();
            MP3 newMP3 = view.getNewMP3Info();
            dao.addMP3(newMP3.getTitle(), newMP3);
            view.displayCreateSuccessBanner();
        }
        
        private void listMP3s() throws MP3DaoException{    
            view.displayDisplayAllBanner();   //telling to display all 
            List<MP3> mp3List = dao.getAllMP3s();
            view.displayMP3List(mp3List);
        }
        
        
       private void findMP3() throws MP3DaoException{
           view.displayDisplayMP3Banner();
           String title = view.getTitleChoice();
           MP3 mp3 = dao.getMP3(title);
           view.displayMP3(mp3);
       }  

       private void removeMP3() throws MP3DaoException{
           view.displayRemoveMP3Banner();
           String title = view.getTitleChoice();
           dao.removeMP3(title);
           view.displayRemoveSuccessBanner();
           
       }
       
       
       private void editMP3() throws MP3DaoException{
           
           view.displayEditMP3Banner();
           String title = view.getTitleChoice();
           MP3 editMP3 = dao.getMP3(title);
          if (view.edMP3(editMP3) == true){
              dao.removeMP3(title);
              dao.addMP3(editMP3.getTitle(),editMP3);
              view.displayContinueBanner();
          }else{
           view.displayContinueBanner();
          
       }
       }
       
       private void unknownCommand(){
           view.displayUnknownCommandBanner();
       }
       private void exitMessage(){
           view.displayExitBanner();
       }
       
    //implement the constructor that initializes MP3View view; and MP3Dao dao;
       public MP3Controller(MP3Dao dao, MP3View view){
         this.dao = dao;
         this.view = view;
       }
            
}
    
    
    
    


