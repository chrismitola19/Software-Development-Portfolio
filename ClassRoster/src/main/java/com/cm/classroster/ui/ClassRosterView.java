
package com.cm.classroster.ui;

import com.cm.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author chris
 */

/*Move the functionality that prints the menu and gets the user's selection to ClassRosterView.
Have the controller use ClassRosterView instead of UserIOConsoleImpl for all the work involved
displaying the menu and getting the user's selection.*/

public class ClassRosterView {
  /*  We'll modify ClassRosterView in the same way we modified the controller. 
    First, remove the code that initializes the io member field. */
           private UserIO io;                                // original - UserIO io = new UserIOConsoleImpl();
    
    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. List Student IDs");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");
        
        return io.readInt("Please select from the above choices.",1,5);
    }
    /*All we have done is move the functionality for printing the menu and getting the user's 
selection from the controller over to the view. Notice that we're using composition here — the 
ClassRosterView has-a UserIO member and it uses UserIO to interact with the user. Remember that UserIO is an interface.*/
    
    //We need to get the information we need from the user to create a new Student object, add this method.
    public Student getNewStudentInfo(){
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");
        
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);
        return currentStudent;
        /*This method prompts the user for Student ID, First Name, Last Name, and Cohort, 
         gathers this information, creates a new Student object, and returns it to the caller.*/
    }
    /*method simply displays a banner or heading to the UI indicating that the next interactions on 
    the screen will be for creating a new student.*/
        public void displayCreateStudentBanner(){
            io.print("=== Create Student ===");
        }
        
    /*method displays a message that the new student was successfully created and waits 
     for the user to hit Enter to continue.*/
        public void displayCreateSuccessBanner(){
            io.readString("Student successfully created. Please hit enter to continue");
        }
        /*Here we'll create a method that takes a list of Student objects as a parameter and displays the
          information for each Student to the screen. After the list has been displayed, the method will 
          pause and wait for the user to hit the Enter key.*/
        public void displayStudentList(List<Student> studentList){
            for (Student currentStudent : studentList){
                io.print(currentStudent.getStudentId() + ": " 
                        + currentStudent.getFirstName() + " "
                        + currentStudent.getLastName());
                //can also enter cohort here 
                        
                      
            }//end of for
            io.readString("Please hit enter to continue.");
        }
        
        /*Now we'll add the method to show the Display All Students banner. Add the following 
         method to your ClassRosterView class:*/
        public void displayDisplayAllBanner (){
            io.print("=== Display all Students ==="); 
        }

        //implement methods for getStudent =
        public void displayDisplayStudentBanner() {   //shows the Display Student banner
            io.print("=== Display Student ===");
        }
        public String getStudentIdChoice(){  //asks the user for the ID of the student he/she wishes to display
            return io.readString("Please enter the Student ID."); 
        }
        public void displayStudent(Student student){ //displays a student's information to the user
            if (student != null){ //if student number exists
                io.print(student.getStudentId());
                io.print(student.getFirstName() + " " + student.getLastName());
                io.print(student.getCohort());
                io.print("");;
            }else {
                io.print("No such student.");
            }
            io.print("Please hit enter to continue.");
        }
        
        /*For this use case, we just need to add two methods to our view: one to display 
          the Remove Student banner and one to display the Remove Success banner.*/
        public void displayRemoveStudentBanner(){
            io.print("=== Remove Student ===");
        }
        public void displayRemoveSuccessBanner(){
            io.readString("Student successfully removed. Please hit enter to continue.");
        }
        
       
        public void displayExitBanner(){
            io.print("Goodbye!!!");
        }
        public void displayUnknownCommandBanner(){
            io.print("Unknown Command!!!");
        }
   
        /*Now we must implement a constructor that initializes the io member field. 
          Add the following constructor to your ClassRosterView:*/
        public ClassRosterView(UserIO io){
            this.io = io;
        }
        
       /* add one more method to our view to display the given error message to the user. 
       We’ll use this method in the controller to display any error messages we encounter*/
        
        public void displayErrorMessage(String errorMsg){
            io.print("=== ERROR ===");
            io.print(errorMsg);
            
        }
        
        
        
        
        
        
        
        
    } 

