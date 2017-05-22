package org.pms.account;

import org.pms.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<Category> getCategoryList(Long categoryGroupId) {
        return categoryDao.getCategoryList(categoryGroupId);
    }
}
