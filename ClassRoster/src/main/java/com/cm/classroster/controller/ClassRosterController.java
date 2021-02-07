
package com.cm.classroster.controller;

import com.cm.classroster.dao.ClassRosterDao;
import com.cm.classroster.dao.ClassRosterPersistenceException;
import com.cm.classroster.dao.ClassRosterDaoFileImpl;
import com.cm.classroster.dto.Student;
import com.cm.classroster.service.ClassRosterDataValidationException;
import com.cm.classroster.service.ClassRosterDuplicateIdException;
import com.cm.classroster.service.ClassRosterServiceLayer;
import com.cm.classroster.ui.ClassRosterView;
import com.cm.classroster.ui.UserIO;
import com.cm.classroster.ui.UserIOConsoleImpl;
import java.util.List;



/**
 *
 * @author chris
 */
//"brains of operation", it will control when the menu system is displayed

public class ClassRosterController {
    
    /*We'll add a member of type ClassRosterView and we'll have the controller use it for displaying
      the menu and getting the user's selection.*/
    //ClassRosterView view = new ClassRosterView();
   // ClassRosterDao dao = new ClassRosterDaoFileImpl(); //we must create a ClassRosterDao member field in our Controller so we can have the DAO store the newly created Student object for us.
   
    //dependency injection
    private ClassRosterView view;
    private ClassRosterServiceLayer service;      //used to be ClassRoster dao; but we replace the calls to Service Layer Methods
    
   
    private UserIO io = (UserIO) new UserIOConsoleImpl();
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
       
        try{ //add try catch 
        while (keepGoing){
            
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection){
                case 1:
                    listStudents(); //make a call to listStudents in the run method when the user selects menu option 1. 
                    break;
                case 2:
                    createStudent(); //make a call to createStudent in the run method when the user selects menu option 2.
                    break;
                case 3: 
                    viewStudent(); //make a call to viewStudent in the run method when the user selects menu option 3.
                    break;
                case 4:
                    removeStudent(); //make a call to removeStudent() in the run method when the user selects menu option 4.
                    break;
                case 5: 
                    keepGoing = false;
                    break;
                default:
                   unknownCommand();                       // was this originally - io.print("UNKOWN COMMAND");
               
            }//end of switch 
        }//end of while
          exitMessage();                                                // was this originally - io.print("GOODBYE");
        
        
    } catch (ClassRosterPersistenceException e){
        view.displayErrorMessage(e.getMessage());
    }
    }//end run
    
    /*Created a new method called getMenuSelection() that we call to get the menuSelection in the run method
      Made a call to printlnMenuAndGetSelection() on the view member*/
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
/*Now we'll create a method in the Controller to orchestrate the creation of a new student. 
  Our method will do the following:
-Display the Create Student banner
-Get all the student data from the user and create the new Student object
-Store the new Student object
-Display the Create Student Success banner
Of course, the Controller won't actually do any of this work — it will have the view and 
the DAO do all the heavy lifting. Add the following method to your controller:*/
   
    
    /*The changes to createStudent() are more extensive than the previous two methods. Notice that we are now calling 
    service.createStudent. You'll remember from the design of our Service Layer interface that this method throws two
    additional exceptions: ClassRosterDuplicateIdException and ClassRosterDataValidationException. Our program can handle
    these two errors by displaying an error message to the user and then re-prompting for the Student data.

    To do this we use a combination of a boolean flag (hasErrors), a do/while loop, and a try/catch construct. If the call
    to createStudent causes an exception we display the error message to the user and set the hasErrors flag to true, 
    which will cause the do/while loop to execute again. This prompts the user for the requested input again. If the call
    to createStudent does no cause an exception, the program displays the success banner and sets the hasErrors flag to 
    false, which will cause the do/while loop to stop repeating.*/
    private void createStudent() throws ClassRosterPersistenceException{ //add throws 
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do{
            Student currentStudent = view.getNewStudentInfo();
            try{
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e){
               hasErrors = true;
               view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    } /*The catch block in this example demonstrates the syntax for handling more than one type of exception in a single 
        catch block. This syntax is straightforward – instead of listing one exception type followed by an 
        identifier, list all the exception types to be handled in the catch block separated by the bitwise
        OR ( | ) operator followed by a single identifier as shown in the example.*/
    
    /*Now we'll add the code to our Controller to orchestrate the necessary activity to list all 
the students in the system. We'll create a method called listStudents that will get a list of all 
Student objects in the system from the DAO and then hand that list to the view to display to the user.*/
    private void listStudents() throws ClassRosterPersistenceException{ //add throws
        List<Student> studentList = service.getAllStudents();
       
       view.displayStudentList(studentList);
    }
    
   /* Here we need to create the viewStudent method. This method asks the view to display the 
View Student banner and get the student ID from the user. Then it asks the DAO for the Student 
associated with the ID. Finally, it asks the view to display the Student information.*/
    private void viewStudent() throws ClassRosterPersistenceException{ //add throws 
       String studentId = view.getStudentIdChoice();
       Student student = service.getStudent(studentId);
       view.displayStudent(student);
       
    }
    
    
/*Here we need to create the removeStudent() method. This method will ask the view to display the 
Remove Student banner and ask the user for the ID of the Student to be removed. It will then ask the DAO 
to remove the Student and, finally, will ask the view to display the Remove Success banner.*/
    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        service.removeStudent(studentId);
        view.displayRemoveSuccessBanner();
    }
    
   /* Here we will add two methods: unknownCommand and exitMessage. These methods just ask the view 
      to display the appropriate message to the user.*/
    private void unknownCommand(){
        view.displayUnknownCommandBanner(); 
    }
    private void exitMessage(){
        view.displayExitBanner();
    }
    
    //Now we must implement a constructor that initializes the io member field.
    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view){
        this.service = service;
        this.view = view;
        
    }
    
    
}
