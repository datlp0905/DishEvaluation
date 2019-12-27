package datlp.entity;

import datlp.entity.SubCategory;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-10T01:40:11")
@StaticMetamodel(MainCategory.class)
public class MainCategory_ { 

    public static volatile SingularAttribute<MainCategory, Date> createdAt;
    public static volatile SingularAttribute<MainCategory, String> name;
    public static volatile ListAttribute<MainCategory, SubCategory> subCategoryList;
    public static volatile SingularAttribute<MainCategory, Integer> id;
    public static volatile SingularAttribute<MainCategory, Integer> hashName;
    public static volatile SingularAttribute<MainCategory, Date> updatedAt;

}