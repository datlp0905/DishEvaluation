package datlp.entity;

import datlp.entity.Dish;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-10T01:40:11")
@StaticMetamodel(Ingredient.class)
public class Ingredient_ { 

    public static volatile SingularAttribute<Ingredient, Double> amount;
    public static volatile SingularAttribute<Ingredient, Double> standardAmount;
    public static volatile SingularAttribute<Ingredient, String> unit;
    public static volatile SingularAttribute<Ingredient, Integer> nutritionHash;
    public static volatile SingularAttribute<Ingredient, Integer> nutritionRecommend;
    public static volatile SingularAttribute<Ingredient, Dish> dishId;
    public static volatile SingularAttribute<Ingredient, String> name;
    public static volatile SingularAttribute<Ingredient, Integer> hashName;
    public static volatile SingularAttribute<Ingredient, Integer> id;
    public static volatile SingularAttribute<Ingredient, Boolean> valuable;
    public static volatile SingularAttribute<Ingredient, Integer> hashContent;
    public static volatile SingularAttribute<Ingredient, Double> energy;

}