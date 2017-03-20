package org.pms.serviceImpls;

import org.pms.daos.CategoryDao;
import org.pms.models.Category;
import org.pms.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tijo on 18/03/17.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }
}
