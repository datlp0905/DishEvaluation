
package datlp.jaxb.nutrition;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for nutritionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="nutritionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nutrition" type="{http://vansu.vn/}ingredientType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nutritionType", propOrder = {
    "nutrition"
})
@XmlRootElement(name = "nutritions")
public class NutritionType {

    @XmlElement(required = true)
    protected List<IngredientType> nutrition;

    /**
     * Gets the value of the nutrition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nutrition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNutrition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IngredientType }
     * 
     * 
     */
    public List<IngredientType> getNutrition() {
        if (nutrition == null) {
            nutrition = new ArrayList<IngredientType>();
        }
        return this.nutrition;
    }

}
