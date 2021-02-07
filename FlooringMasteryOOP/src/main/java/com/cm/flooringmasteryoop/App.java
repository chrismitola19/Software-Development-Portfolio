package com.cm.flooringmasteryoop;

import com.cm.flooringmasteryoop.controller.FlooringMasteryController;

//import com.cm.flooringmasteryoop.dao.FlooringMasteryDao;
//import com.cm.flooringmasteryoop.dao.FlooringMasteryDaoFileImpl;
//import com.cm.flooringmasteryoop.service.FlooringMasteryServiceLayer;
//import com.cm.flooringmasteryoop.service.FlooringMasteryServiceLayerImpl;
//import com.cm.flooringmasteryoop.ui.FlooringMasteryView;
//import com.cm.flooringmasteryoop.ui.UserIO;
//import com.cm.flooringmasteryoop.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chris
 */
public class App {
	    
	    public static void main(String[] args) {
//	        UserIO myIo = new UserIOConsoleImpl();
//                FlooringProgramView view = new FlooringProgramView(myIo);
//                FlooringProgramDao dao = new FlooringProgramDaoFileImpl();              
//                FlooringProgramServiceLayer service = new FlooringProgramServiceLayerImpl(dao);
//                FlooringProgramController controller = new FlooringProgramController(view, service);
//	          controller.run();

               
               ApplicationContext ctx 
                       = new ClassPathXmlApplicationContext("applicationContext.xml");
                
                FlooringMasteryController controller
                        = ctx.getBean("controller", FlooringMasteryController.class);
               controller.run();
	      
	}
	
}