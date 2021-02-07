
package com.cm.mp3library;

import com.cm.mp3library.controller.MP3Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chris
 */
public class App {
    public static void main(String[] args) {
//        //configures, instantiates, initalize, and assembles classes in our application
//        UserIO myIo = new UserIOConsoleImpl();
//        MP3View myView = new MP3View(myIo);
//        MP3Dao myDao = new MP3DaoFileImpl();
//        MP3Controller controller = new MP3Controller(myDao, myView); //looks into AddressBookController      
//        controller.run(); //call run method

        //instantiate Spring 
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        //grab controller and call run
        MP3Controller controller = ctx.getBean("controller", MP3Controller.class); 
        controller.run();
        
    }
}
