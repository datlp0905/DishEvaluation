/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.resolver;

import datlp.checker.XmlSyntaxChecker;
import datlp.utilities.HttpUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author DATLPSE62823
 */
public class MyURIResolver implements URIResolver{
    private int count = 0;
    @Override
    public Source resolve(String href, String base) throws TransformerException {
        if (href != null && href.indexOf("https://monngonmoingay.com/") == 0
                            || href.indexOf("http://vansu.vn/") == 0) {
            try {
                String content = HttpUtils.getHttpContent(href);
                XmlSyntaxChecker checker = new XmlSyntaxChecker();
                content = checker.refineHtml(content);
                content = checker.check(content); 
                
                InputStream is = new ByteArrayInputStream(content.toString().getBytes(StandardCharsets.UTF_8));
                System.out.println("Count: " + ++count + " Href: " + href);
                return new StreamSource(is);
            } catch (IOException ex) {
                Logger.getLogger(MyURIResolver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
}
