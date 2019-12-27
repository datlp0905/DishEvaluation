/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.utilities;

import datlp.jaxb.JaxbValidationHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author DATLPSE62823
 */
public class JaxbUtils {

    /**
     *
     * @param type
     * @param is
     * @param schemaFilePath
     * @return
     * @throws JAXBException
     * @throws SAXException
     */
    public static Object unmarshall(Class type, InputStream is, String schemaFilePath) 
            throws JAXBException, SAXException {
        JAXBContext jc = JAXBContext.newInstance(type);
        Unmarshaller u = jc.createUnmarshaller();
        u.setEventHandler(new JaxbValidationHandler());
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(schemaFilePath));
        u.setSchema(schema);
        
        return u.unmarshal(is);
    }
    
    public static Object unmarshall(Class type, FileInputStream fis, String schemaFilePath) 
            throws JAXBException, SAXException {
        JAXBContext jc = JAXBContext.newInstance(type);
        Unmarshaller u = jc.createUnmarshaller();
        u.setEventHandler(new JaxbValidationHandler());
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(schemaFilePath));
        u.setSchema(schema);
        
        return u.unmarshal(fis);
    }
}
