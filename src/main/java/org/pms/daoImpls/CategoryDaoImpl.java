package org.pms.daoImpls;

import org.hibernate.criterion.Restrictions;
import org.pms.daos.CategoryDao;
import org.pms.models.Category;
import org.springframework.stereotype.Repository;

/**
 * Created by tijo on 18/03/17.
 */
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {

    public CategoryDaoImpl() {
        setType(Category.class);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return (Category) getDb(false).createCriteria(Category.class, "category").add(Restrictions.eq("category.id", categoryId)).uniqueResult();
    }
}
