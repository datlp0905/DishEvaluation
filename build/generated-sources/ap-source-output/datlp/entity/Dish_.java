package datlp.entity;

import datlp.entity.DishSubCategory;
import datlp.entity.Ingredient;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-10T01:40:11")
@StaticMetamodel(Dish.class)
public class Dish_ { 

    public static volatile SingularAttribute<Dish, String> img;
    public static volatile SingularAttribute<Dish, String> updatedBy;
    public static volatile SingularAttribute<Dish, String> level;
    public static volatile SingularAttribute<Dish, String> cookTime;
    public static volatile SingularAttribute<Dish, String> link;
    public static volatile SingularAttribute<Dish, String> description;
    public static volatile SingularAttribute<Dish, Integer> hashName;
    public static volatile SingularAttribute<Dish, Integer> hashContent;
    public static volatile ListAttribute<Dish, DishSubCategory> dishSubCategoryList;
    public static volatile SingularAttribute<Dish, String> recipeYield;
    public static volatile SingularAttribute<Dish, Date> createdAt;
    public static volatile SingularAttribute<Dish, String> createdBy;
    public static volatile SingularAttribute<Dish, String> name;
    public static volatile ListAttribute<Dish, Ingredient> ingredientList;
    public static volatile SingularAttribute<Dish, Integer> id;
    public static volatile SingularAttribute<Dish, Date> updatedAt;

}