package com.rwandamedia.dao;

import com.rwandamedia.system.InMemoryDatabase;
import com.rwandamedia.models.Category;
import java.util.List;

public class CategoryDAO {
    
    public List<Category> getAllCategories() {
        return InMemoryDatabase.getAllCategories();
    }
    
    public boolean addCategory(Category category) {
        System.out.println("Category added: " + category.getName());
        return true;
    }
    
    public boolean updateCategory(Category category) {
        System.out.println("Category updated: " + category.getName());
        return true;
    }
    
    public boolean deleteCategory(int categoryID) {
        System.out.println("Category deleted: " + categoryID);
        return true;
    }
    
    public Category getCategoryById(int categoryID) {
        List<Category> categories = getAllCategories();
        for (Category category : categories) {
            if (category.getCategoryID() == categoryID) {
                return category;
            }
        }
        return null;
    }
}