
package com.cm.classroster.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author chris
 */
public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao {
 
    public static final String AUDIT_FILE = "audit.txt";
   
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new ClassRosterPersistenceException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
 
}

/*Some things to note about this implementation:

We are opening the audit file in append mode so that each entry will be appended to the file rather than overwriting 
everything that was there before. We accomplish this by setting the second parameter of the FileWriter constructor to true:
We are using a LocalDateTime object to create a timestamp for our audit log entries. Don't worry too much about
LocalDateTime now – just use it as shown here. We will learn about the Java Date-Time API in a later lesson.*/
