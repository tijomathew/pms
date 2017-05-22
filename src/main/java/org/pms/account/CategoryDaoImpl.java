package org.pms.account;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.pms.common.dao.GenericDaoImpl;
import org.pms.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Category> getCategoryList(Long categoryGroupId) {
        return getDb(false).createCriteria(Category.class, "category").createAlias("category.categoryGroup", "categoryGroup").add(Restrictions.eq("categoryGroup.id", categoryGroupId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
