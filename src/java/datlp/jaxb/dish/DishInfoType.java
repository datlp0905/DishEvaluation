
package datlp.jaxb.dish;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dishInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dishInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mainCategory" type="{https://monngonmoingay.com/}mainCategoryType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dishInfoType", propOrder = {
    "mainCategory"
})
@XmlRootElement(name = "dishInfo")
public class DishInfoType {

    @XmlElement(required = true)
    protected List<MainCategoryType> mainCategory;

    /**
     * Gets the value of the mainCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mainCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMainCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MainCategoryType }
     * 
     * 
     */
    public List<MainCategoryType> getMainCategory() {
        if (mainCategory == null) {
            mainCategory = new ArrayList<MainCategoryType>();
        }
        return this.mainCategory;
    }

}
