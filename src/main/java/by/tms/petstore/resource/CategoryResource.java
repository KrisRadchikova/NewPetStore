package by.tms.petstore.resource;

import by.tms.petstore.model.Category;
import by.tms.petstore.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/category")
@Slf4j
public class CategoryResource {

    private CategoryService categoryService;

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Category>> getAllCategories() {
        log.info("Get all catigories");
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{categoryId}")
    public Optional<Category> findCategoryById(@PathVariable long categoryId) {
        log.info("Find category with ID " + categoryId);
        return categoryService.findCategoryById(categoryId);
    }

    @PostMapping(path = "/add")
    public boolean addNewCategory(@RequestBody Category category) {
        log.info("Add new category " + category.getName());
        categoryService.addNewCategory(category);
        return true;
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteCategory(@PathVariable long id) {
        log.info("Delete category with ID " + id);
        categoryService.deleteCategoryById(id);
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
