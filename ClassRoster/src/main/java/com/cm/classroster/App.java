/*All we have done is move the functionality for printing the menu and getting the user's 
selection from the controller over to the view. Notice that we're using composition here — the 
ClassRosterView has-a UserIO member and it uses UserIO to interact with the user. Remember that UserIO is an interface.*/
package com.cm.classroster;

import com.cm.classroster.controller.ClassRosterController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 *
 * @author chris
 */

/*We have all the components modified for dependency injection, so now we must modify the 
main method in our App class so that it configures, instantiates, and assembles the classes in our application. 
Our code will do the following:

-Declare a UserIO variable and initialize it with a UserIOConsoleImpl reference.
-Declare and instantiate a ClassRosterView object, passing the UserIO created in the previous step into the constructor.
-Declare a ClassRosterDao variable and initialize it with a ClassRosterDaoFileImpl reference.
-Instantiate a ClassRosterController, passing the ClassRosterDao and ClassRosterView object into the constructor.
-Call the run method on the controller to kick things off.*/

public class App {   //wiring up application (doing Dependency injection)
    public static void main(String[] args){
//     // Instantiate the UserIO implementation
//    UserIO myIo = new UserIOConsoleImpl();
//    // Instantiate the View and wire the UserIO implementation into it
//    ClassRosterView myView = new ClassRosterView(myIo);
//    // Instantiate the DAO
//    ClassRosterDao myDao = new ClassRosterDaoFileImpl();
//    // Instantiate the Audit DAO
//    ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//    // Instantiate the Service Layer and wire the DAO and Audit DAO into it
//    ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
//    // Instantiate the Controller and wire the Service Layer into it
//    ClassRosterController controller = new ClassRosterController(myService, myView);
//    // Kick off the Controller
//    controller.run();
//    
    //instantiate Spring container 
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    //asking context to hand back controller
    ClassRosterController controller = ctx.getBean("controller", ClassRosterController.class); //give me bean with alias controller, return it back to me as ClasRosterController
    
    controller.run();   //call run on controller
    }
}
