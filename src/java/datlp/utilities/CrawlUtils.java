/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.utilities;

import datlp.resolver.MyURIResolver;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author DATLPSE62823
 */
public class CrawlUtils {
    
    public static ByteArrayOutputStream crawl(String configPath, String xslPath) 
            throws FileNotFoundException, TransformerConfigurationException, 
            TransformerException{
        StreamSource xslCate = new StreamSource(xslPath);
        InputStream is = new FileInputStream(configPath);
        TransformerFactory factory = TransformerFactory.newInstance();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        StreamResult rs = new StreamResult(os);
        MyURIResolver resolver = new MyURIResolver();
        factory.setURIResolver(resolver);
        Transformer transformer = factory.newTransformer(xslCate);
        transformer.transform(new StreamSource(is), rs);
        return os;
    }
    
    public static DOMResult crawlWithDOM(String configPath, String xslPath) 
            throws FileNotFoundException, TransformerConfigurationException, 
            TransformerException{
        StreamSource xslCate = new StreamSource(xslPath);
        InputStream is = new FileInputStream(configPath);
        TransformerFactory factory = TransformerFactory.newInstance();
        DOMResult result = new DOMResult();
        MyURIResolver resolver = new MyURIResolver();
        factory.setURIResolver(resolver);
        Transformer transformer = factory.newTransformer(xslCate);
        transformer.transform(new StreamSource(is), result);
        return result;
    }
}
