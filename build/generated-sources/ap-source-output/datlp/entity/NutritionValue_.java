package datlp.entity;

import datlp.entity.Nutrition;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-10T01:40:11")
@StaticMetamodel(NutritionValue.class)
public class NutritionValue_ { 

    public static volatile SingularAttribute<NutritionValue, Date> createdAt;
    public static volatile SingularAttribute<NutritionValue, Double> amount;
    public static volatile SingularAttribute<NutritionValue, String> unit;
    public static volatile SingularAttribute<NutritionValue, Nutrition> nutriId;
    public static volatile SingularAttribute<NutritionValue, String> name;
    public static volatile SingularAttribute<NutritionValue, Integer> id;
    public static volatile SingularAttribute<NutritionValue, Integer> hashName;
    public static volatile SingularAttribute<NutritionValue, Integer> hashContent;
    public static volatile SingularAttribute<NutritionValue, Date> updatedAt;

}