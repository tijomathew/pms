package org.pms.account;

import org.pms.domain.Category;

import java.util.List;

/**
 * Created by tijo on 18/03/17.
 */
public interface CategoryService {

    Category getCategoryById(Long categoryId);

    List<Category> getCategoryList(Long categoryGroupId);
}
