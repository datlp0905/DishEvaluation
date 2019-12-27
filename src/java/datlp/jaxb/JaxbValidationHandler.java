/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.jaxb;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

/**
 *
 * @author DATLPSE62823
 */
public class JaxbValidationHandler implements ValidationEventHandler{

    @Override
    public boolean handleEvent(ValidationEvent event) {
        if(event.getSeverity() == ValidationEvent.FATAL_ERROR
                || event.getSeverity() == ValidationEvent.ERROR) {
            ValidationEventLocator locator = event.getLocator();
            
            System.out.println("Invalid document: " + locator.getURL());
            System.out.println("Error: " + event.getMessage());
            System.out.println("Error at column " + locator.getColumnNumber() +
                    ", line " + locator.getLineNumber());
        }
        
        return true;
    }
    
}
