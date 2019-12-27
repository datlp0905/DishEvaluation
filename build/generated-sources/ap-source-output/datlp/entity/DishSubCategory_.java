package datlp.entity;

import datlp.entity.Dish;
import datlp.entity.SubCategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-10T01:40:11")
@StaticMetamodel(DishSubCategory.class)
public class DishSubCategory_ { 

    public static volatile SingularAttribute<DishSubCategory, SubCategory> subCateId;
    public static volatile SingularAttribute<DishSubCategory, Dish> dishId;
    public static volatile SingularAttribute<DishSubCategory, Integer> id;

}