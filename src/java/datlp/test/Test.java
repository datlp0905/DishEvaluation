/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.test;

import datlp.entity.BMIConstanst;
import datlp.entity.Dish;
import datlp.jaxb.nutrition.IngredientType;
import datlp.jaxb.nutrition.NutritionType;
import datlp.repository.DishRepository;
import datlp.repository.DishRepositoryImpl;
import datlp.service.BMIConstanstService;
import datlp.service.BMIConstanstServiceImpl;
import datlp.utilities.CrawlUtils;
import datlp.utilities.StringUtils;
import static datlp.utilities.StringUtils.computeMatchingDensity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author DATLPSE62823
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException, TransformerException {
//        NutritionGroupService nutritionGroupService = new NutritionGroupServiceImpl();
//        List<NutritionGroup> nutritionGroups = nutritionGroupService.findAll();
//        System.out.println(nutritionGroups.size());
//        crawlWithSchemaValidation();
//        testUTF8();
//        testStringMatching();
//        testGetEnergy();
        testBMIRepo();
    }

    private static void crawlWithSchemaValidation() throws FileNotFoundException, TransformerException {
        OutputStream os = new ByteArrayOutputStream();
        ByteArrayOutputStream rs = CrawlUtils.crawl("web/WEB-INF/vansu/input-nutritions.xml", "web/WEB-INF/vansu/nutrition-main.xsl");
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        StreamResult sr = new StreamResult(new FileOutputStream("output-nutritions1.xml"));
    }

    private static void testStringMatching() {
        String a, b, exit = "";
        Scanner sc = new Scanner(System.in);
        while (!"x".equals(exit)) {
            System.out.print("a = ");
            a = sc.nextLine();
            System.out.print("b = ");
            b = sc.nextLine();
            float matching = computeMatchingDensity(a, b);
            float matchingPercent = matching / Math.min(a.length(), b.length()) * 100;
            System.out.println("Matching " + matchingPercent);
            System.out.println("Continute?");
            exit = sc.nextLine();
        }
    }
    
    private static void testUTF8() {
        String myFuckingString = "MÃ¬, miáº¿n... Äƒn liá»�n BÃºn Äƒn liá»�n";
        byte[] pText = myFuckingString.getBytes(StandardCharsets.ISO_8859_1);
        String value = new String(pText, StandardCharsets.UTF_8);
        System.out.println(myFuckingString + "|" + value);
    }
    
    private static void testGetEnergy() {
        DishRepository dishRepository = new DishRepositoryImpl();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("from", 583);
        parameters.put("to", 683);
        
        List<Dish> dishes = dishRepository.findMany("Dish.finByEnergyFromTo", parameters);
        for (Dish dish : dishes) {
            System.out.println(dish.getName() + " " + dish.getTotalEnergy());
        }
    }
    
    private static void testBMIRepo() {
        BMIConstanstService service = new BMIConstanstServiceImpl();
        BMIConstanst constanst = service.findLastest();
        System.out.println(constanst.getBMILevelOfWorkList().size());
    }
}
