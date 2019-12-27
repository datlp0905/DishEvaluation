
package datlp.jaxb.nutrition;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the datlp.jaxb.nutrition package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Nutritions_QNAME = new QName("http://vansu.vn/", "nutritions");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: datlp.jaxb.nutrition
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NutritionType }
     * 
     */
    public NutritionType createNutritionType() {
        return new NutritionType();
    }

    /**
     * Create an instance of {@link IngredientType }
     * 
     */
    public IngredientType createIngredientType() {
        return new IngredientType();
    }

    /**
     * Create an instance of {@link NutritionValueType }
     * 
     */
    public NutritionValueType createNutritionValueType() {
        return new NutritionValueType();
    }

    /**
     * Create an instance of {@link NutritionValuesType }
     * 
     */
    public NutritionValuesType createNutritionValuesType() {
        return new NutritionValuesType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NutritionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vansu.vn/", name = "nutritions")
    public JAXBElement<NutritionType> createNutritions(NutritionType value) {
        return new JAXBElement<NutritionType>(_Nutritions_QNAME, NutritionType.class, null, value);
    }

}
