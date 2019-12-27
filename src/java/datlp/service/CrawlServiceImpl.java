/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.service;

import static datlp.constants.Constants.*;
import datlp.jaxb.dish.DishInfoType;
import datlp.jaxb.nutrition.NutritionType;
import datlp.utilities.CrawlUtils;
import datlp.utilities.JaxbUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author DATLPSE62823
 */
public class CrawlServiceImpl implements CrawlService {

    private String inputFilePath, xslFilePath, schemaFilePath;

    public CrawlServiceImpl() {
    }

    @Override
    public void crawlNutrition(String realPath) {
        ByteArrayOutputStream os;
        inputFilePath = realPath + NUTRITION_INPUT_PATH;
        xslFilePath = realPath + NUTRITION_XSL_PATH;
        schemaFilePath = realPath + NUTRITION_SCHEMA_PATH;

        try {
            os = CrawlUtils.crawl(inputFilePath, xslFilePath);
            NutritionType nutritionType = (NutritionType) JaxbUtils.unmarshall(NutritionType.class,
                    new ByteArrayInputStream(os.toByteArray()), schemaFilePath);
            //Save to DB
            NutritionService nutritionService = new NutritionServiceImpl();
            nutritionService.saveNutritionToDB(nutritionType);
        } catch (FileNotFoundException | TransformerException | JAXBException | SAXException ex) {
            Logger.getLogger(CrawlServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crawlDish(String realPath) {
        ByteArrayOutputStream os;
        inputFilePath = realPath + DISH_INPUT_PATH;
        xslFilePath = realPath + DISH_XSL_PATH;
        schemaFilePath = realPath + DISH_SCHEMA_PATH;

        try {
            os = CrawlUtils.crawl(inputFilePath, xslFilePath);
            DishInfoType dishInfoType = (DishInfoType) JaxbUtils.unmarshall(DishInfoType.class,
                    new ByteArrayInputStream(os.toByteArray()), schemaFilePath);
            //Save to DB
            DishService dishService = new DishServiceImpl();
            dishService.saveDishToDB(dishInfoType);
        } catch (FileNotFoundException | TransformerException | JAXBException | SAXException ex) {
            Logger.getLogger(CrawlServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
