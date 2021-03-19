package by.tms.petstore.service;

import by.tms.petstore.model.Category;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
//resources
class CategoryServiceTest {

    @Autowired
    public CategoryService categoryService;


    @Test
    @Order(1)
    void contextLoads() {
        assertThat(categoryService).isNotNull();
    }


    @Test()
    @Order(2)
    void addNewCategoryTest() {
        Category category = new Category("CatTest");
        boolean isCategoryCreated = categoryService.addNewCategory(category);
        assertTrue(isCategoryCreated);
        assertEquals("CatTest", categoryService.findCategoryById(category.getId()).get().getName());
    }
    @Test
    @Order(4)
    void getAllCategoriesTest() {
        List<Category> categories = categoryService.getAllCategories();
        assertEquals(1, categories.size()); // --?
    }

    @Test
    @Order(3)
    void findCategoryByIdTest() {
        Optional<Category> category = categoryService.findCategoryById(1);
        assertTrue(category.isPresent());
        assertEquals("CatTest", category.get().getName());
    }

    @Test
    @Order(5)
    void deleteCategoryByIdTest() {
        String category = categoryService.deleteCategoryById(1);
        assertNull(category);

    }
}
