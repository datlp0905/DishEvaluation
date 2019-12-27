package datlp.entity;

import datlp.entity.NutritionGroup;
import datlp.entity.NutritionValue;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-10T01:40:11")
@StaticMetamodel(Nutrition.class)
public class Nutrition_ { 

    public static volatile SingularAttribute<Nutrition, Double> calculatedPer;
    public static volatile SingularAttribute<Nutrition, Date> createdAt;
    public static volatile ListAttribute<Nutrition, NutritionValue> nutritionValueList;
    public static volatile SingularAttribute<Nutrition, NutritionGroup> groupId;
    public static volatile SingularAttribute<Nutrition, String> name;
    public static volatile SingularAttribute<Nutrition, Double> energyAmount;
    public static volatile SingularAttribute<Nutrition, Integer> id;
    public static volatile SingularAttribute<Nutrition, Integer> hashName;
    public static volatile SingularAttribute<Nutrition, String> energyUnit;
    public static volatile SingularAttribute<Nutrition, Integer> hashContent;
    public static volatile SingularAttribute<Nutrition, Date> updatedAt;

}