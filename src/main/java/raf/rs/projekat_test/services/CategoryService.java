package raf.rs.projekat_test.services;

import raf.rs.projekat_test.entities.Category;
import raf.rs.projekat_test.repositories.category.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {


    @Inject
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

    public List<Category> allCategories() {
        return this.categoryRepository.allCategories();
    }

    public Category findCategory(Integer id) {
        return this.categoryRepository.findCategory(id);
    }

    public Category findCategoryByName(String name) {
        return this.categoryRepository.findCategoryByName(name);
    }

    public Category updateCategory(Integer id, Category category) {
        return this.categoryRepository.updateCategory(id, category);
    }

    public void deleteCategory(Integer id) {
        this.categoryRepository.deleteCategory(id);
    }

}
