package datlp.entity;

import datlp.entity.Nutrition;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-10T01:40:11")
@StaticMetamodel(NutritionGroup.class)
public class NutritionGroup_ { 

    public static volatile SingularAttribute<NutritionGroup, Date> createdAt;
    public static volatile ListAttribute<NutritionGroup, Nutrition> nutritionList;
    public static volatile SingularAttribute<NutritionGroup, String> name;
    public static volatile SingularAttribute<NutritionGroup, Integer> id;
    public static volatile SingularAttribute<NutritionGroup, Integer> hashName;
    public static volatile SingularAttribute<NutritionGroup, Date> updatedAt;

}