package org.pms.custompropertyeditors;

import org.pms.models.Category;
import org.pms.services.CategoryService;

import java.beans.PropertyEditorSupport;

/**
 * Created by tijo on 18/03/17.
 */
public class CategoryCustomPropertyEditor extends PropertyEditorSupport {

    private final CategoryService categoryService;

    public CategoryCustomPropertyEditor(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    public void setAsText(String categoryId) {
        if (categoryId != null && !categoryId.isEmpty()) {
            Long categoryIdFromUI = Long.valueOf(categoryId);
            Category selectedCategory = categoryService.getCategoryById(categoryIdFromUI);
            setValue(selectedCategory);
        }
    }

}
