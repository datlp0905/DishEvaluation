
package datlp.jaxb.dish;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the datlp.jaxb.dish package. 
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

    private final static QName _DishInfo_QNAME = new QName("https://monngonmoingay.com/", "dishInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: datlp.jaxb.dish
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DishInfoType }
     * 
     */
    public DishInfoType createDishInfoType() {
        return new DishInfoType();
    }

    /**
     * Create an instance of {@link IngredientType }
     * 
     */
    public IngredientType createIngredientType() {
        return new IngredientType();
    }

    /**
     * Create an instance of {@link SubCategoryType }
     * 
     */
    public SubCategoryType createSubCategoryType() {
        return new SubCategoryType();
    }

    /**
     * Create an instance of {@link MainCategoryType }
     * 
     */
    public MainCategoryType createMainCategoryType() {
        return new MainCategoryType();
    }

    /**
     * Create an instance of {@link DishType }
     * 
     */
    public DishType createDishType() {
        return new DishType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DishInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://monngonmoingay.com/", name = "dishInfo")
    public JAXBElement<DishInfoType> createDishInfo(DishInfoType value) {
        return new JAXBElement<DishInfoType>(_DishInfo_QNAME, DishInfoType.class, null, value);
    }

}
