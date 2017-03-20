package org.pms.daos;

import org.pms.models.Category;

/**
 * Created by tijo on 18/03/17.
 */
public interface CategoryDao {

    Category getCategoryById(Long categoryId);
}
