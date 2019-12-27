/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.jaxb;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author DATLPSE62823
 */
public class XJCGenerateJavaObj {
    public static void main(String[] args) {
        try {
            String output = "src/java";
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {
                
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println("Error: " + saxpe.getMessage());
                }
                
                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println("Fatal: " + saxpe.getMessage());
                }
                
                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println("Warning: " + saxpe.getMessage());
                }
                
                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println("Info: " + saxpe.getMessage());
                }
            });
//            sc.forcePackageName("datlp.jaxb.nutrition");
            sc.forcePackageName("datlp.jaxb.dish");
            
//            File schema = new File("web/WEB-INF/schema/nutrition.xsd");
            File schema = new File("web/WEB-INF/schema/dish.xsd");
            InputSource is = new InputSource(schema.toURI().toString());
            sc.parseSchema(is);
            S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(output));
        } catch (IOException ex) {
            Logger.getLogger(XJCGenerateJavaObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
