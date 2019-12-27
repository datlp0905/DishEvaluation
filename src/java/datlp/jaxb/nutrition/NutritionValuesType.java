package datlp.jaxb.nutrition;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for nutritionValuesType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="nutritionValuesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="valueName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nutritionValuesType", propOrder = {
    "valueName",
    "amount",
    "unit"
})
public class NutritionValuesType {

    @XmlElement(required = true)
    protected String valueName;
    @XmlElement(required = true, defaultValue = "0")
    protected BigDecimal amount;
    @XmlElement(required = true)
    protected String unit;

    /**
     * Gets the value of the valueName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getValueName() {
        return valueName;
    }

    /**
     * Sets the value of the valueName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setValueName(String value) {
        this.valueName = value;
    }

    /**
     * Gets the value of the amount property.
     *
     * @return possible object is {@link BigDecimal }
     *
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     *
     * @param value allowed object is {@link BigDecimal }
     *
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the unit property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    @Override
    public String toString() {
        return this.amount + this.unit; //To change body of generated methods, choose Tools | Templates.
    }
}
