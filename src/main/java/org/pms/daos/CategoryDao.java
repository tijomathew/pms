package org.pms.daos;

import org.pms.models.Category;

import java.util.List;

/**
 * Created by tijo on 18/03/17.
 */
public interface CategoryDao {

    Category getCategoryById(Long categoryId);

    List<Category> getCategoryList(Long categoryGroupId);
}
