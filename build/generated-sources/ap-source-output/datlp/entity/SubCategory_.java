package datlp.entity;

import datlp.entity.DishSubCategory;
import datlp.entity.MainCategory;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-10T01:40:11")
@StaticMetamodel(SubCategory.class)
public class SubCategory_ { 

    public static volatile SingularAttribute<SubCategory, Date> createdAt;
    public static volatile SingularAttribute<SubCategory, String> name;
    public static volatile SingularAttribute<SubCategory, Integer> id;
    public static volatile SingularAttribute<SubCategory, Integer> hashName;
    public static volatile SingularAttribute<SubCategory, MainCategory> mainCateId;
    public static volatile SingularAttribute<SubCategory, Date> updatedAt;
    public static volatile ListAttribute<SubCategory, DishSubCategory> dishSubCategoryList;

}