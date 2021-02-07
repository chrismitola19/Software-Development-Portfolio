
package com.cm.classroster.dao;

/**
 *
 * @author chris
 */
public interface ClassRosterAuditDao {
    
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
    
}
/*The Audit DAO is very simple – it has just one method that writes an entry to the log file. 
Because this method can run into problems writing to the audit file, it throws ClassRosterPersistenceException.*/