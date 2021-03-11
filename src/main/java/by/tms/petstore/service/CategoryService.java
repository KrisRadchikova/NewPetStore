package by.tms.petstore.service;

import by.tms.petstore.model.Category;
import by.tms.petstore.repository.CategoryRepository;
import by.tms.petstore.resource.exception.ExistsException;
import by.tms.petstore.resource.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    //get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //add new category
    public boolean addNewCategory(Category category) {
        ExampleMatcher addNewCategoryMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("name", ignoreCase());
        Example<Category> categoryExample = Example.of(category, addNewCategoryMatcher);
        if (!categoryRepository.exists(categoryExample)) {
            categoryRepository.save(category);
            return true;
        }
        throw new ExistsException("This category exists");
    }

    //find category by id
    public Optional<Category> findCategoryById(long id) {
        if (categoryRepository.findById(id).isPresent()) {
            return categoryRepository.findById(id);
        } else {
            throw new NotFoundException("Category not found");
        }
    }

    //delete category
    public void deleteCategoryById(long id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new NotFoundException("Category with id " + id + " not found");
        }
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

}
