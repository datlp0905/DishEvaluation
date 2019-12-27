package datlp.jaxb.nutrition;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ingredientType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ingredientType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nutriGroup" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="calculatedPer" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="nutriName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="energyAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="energyUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nutritionValues" type="{http://vansu.vn/}nutritionValueType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ingredientType", propOrder = {
    "nutriGroup",
    "calculatedPer",
    "nutriName",
    "energyAmount",
    "energyUnit",
    "nutritionValues"
})
public class IngredientType {

    @XmlElement(required = true)
    protected String nutriGroup;
    @XmlElement(required = true, defaultValue = "100")
    protected BigDecimal calculatedPer;
    @XmlElement(required = true)
    protected String nutriName;
    @XmlElement(required = true)
    protected BigDecimal energyAmount;
    @XmlElement(required = true)
    protected String energyUnit;
    @XmlElement(required = true)
    protected NutritionValueType nutritionValues;

    /**
     * Gets the value of the nutriGroup property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNutriGroup() {
        return nutriGroup;
    }

    /**
     * Sets the value of the nutriGroup property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNutriGroup(String value) {
        this.nutriGroup = value;
    }

    /**
     * Gets the value of the calculatedPer property.
     *
     * @return possible object is {@link BigDecimal }
     *
     */
    public BigDecimal getCalculatedPer() {
        return calculatedPer;
    }

    /**
     * Sets the value of the calculatedPer property.
     *
     * @param value allowed object is {@link BigDecimal }
     *
     */
    public void setCalculatedPer(BigDecimal value) {
        this.calculatedPer = value;
    }

    /**
     * Gets the value of the nutriName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNutriName() {
        return nutriName;
    }

    /**
     * Sets the value of the nutriName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNutriName(String value) {
        this.nutriName = value;
    }

    /**
     * Gets the value of the energyAmount property.
     *
     * @return possible object is {@link BigDecimal }
     *
     */
    public BigDecimal getEnergyAmount() {
        return energyAmount;
    }

    /**
     * Sets the value of the energyAmount property.
     *
     * @param value allowed object is {@link BigDecimal }
     *
     */
    public void setEnergyAmount(BigDecimal value) {
        this.energyAmount = value;
    }

    /**
     * Gets the value of the energyUnit property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEnergyUnit() {
        return energyUnit;
    }

    /**
     * Sets the value of the energyUnit property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEnergyUnit(String value) {
        this.energyUnit = value;
    }

    /**
     * Gets the value of the nutritionValues property.
     *
     * @return possible object is {@link NutritionValueType }
     *
     */
    public NutritionValueType getNutritionValues() {
        return nutritionValues;
    }

    /**
     * Sets the value of the nutritionValues property.
     *
     * @param value allowed object is {@link NutritionValueType }
     *
     */
    public void setNutritionValues(NutritionValueType value) {
        this.nutritionValues = value;
    }

    @Override
    public String toString() {
        return this.calculatedPer.toString() + this.energyAmount + this.energyUnit;
    }

}
