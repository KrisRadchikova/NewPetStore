package by.tms.petstore.resource;

import by.tms.petstore.model.Category;
import by.tms.petstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/category")
public class CategoryResource {

    private CategoryService categoryService;

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{categoryId}")
    public Optional<Category> findCategoryById(@PathVariable long categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @PostMapping(path = "/add")
    public Category addNewCategory(@RequestBody Category category) {
        return categoryService.addNewCategory(category);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteCategory(@PathVariable long id) {
        categoryService.deleteCategoryById(id);
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
