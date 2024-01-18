package raf.rs.projekat_test.repositories.category;

import raf.rs.projekat_test.entities.Category;
import raf.rs.projekat_test.entities.User;

import java.util.List;

public interface CategoryRepository {

    public Category addCategory(Category category);
    public List<Category> allCategories();
    public Category findCategory(Integer id);
    public Category findCategoryByName(String name);
    public Category updateCategory(Integer id, Category category);
    public void deleteCategory(Integer id);

}
