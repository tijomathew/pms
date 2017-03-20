package org.pms.custompropertyeditors;

import org.pms.models.Category;
import org.pms.models.CategoryGroup;
import org.pms.services.CategoryService;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;

/**
 * Created by tijo on 18/03/17.
 */
public class CategoryCustomPropertyEditor extends PropertyEditorSupport {

    private final CategoryService categoryService;

    public CategoryCustomPropertyEditor(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    @Override
    public void setAsText(String categoryId) throws IllegalArgumentException {
        if (categoryId != null && !categoryId.isEmpty()) {
            Long categoryIdFromUI = Long.valueOf(categoryId);
            Category selectedCategory = categoryService.getCategoryById(categoryIdFromUI);
            setValue(selectedCategory);
        }
    }
}
