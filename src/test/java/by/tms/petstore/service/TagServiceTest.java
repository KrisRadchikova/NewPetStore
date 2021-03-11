package by.tms.petstore.service;

import by.tms.petstore.model.Tag;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test") //resources
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TagServiceTest {

    @Autowired
    public TagService tagService;

    @Test
    @Order(1)
    void contextLoads() {
        assertThat(tagService).isNotNull();
    }

    @Test
    @Order(2)
    void addNewTag() {
        Tag tag = tagService.addNewTag(new Tag("sphinx"));
        Tag tag2 = tagService.addNewTag(new Tag("abyssinian"));
        assertEquals("sphinx", tag.getName());
        assertEquals("abyssinian", tag2.getName());

    }

    @Test
    @Order(4)
    void getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        assertEquals(2, tags.size());
    }


    @Test
    @Order(3)
    void findTagById() {
        Optional<Tag> tag = tagService.findTagById(1);
        assertTrue(tag.isPresent());
    }

    @Test
    @Order(5)
    void deleteTagById() {
        tagService.deleteTagById(1);
        assertEquals(1, tagService.getAllTags().size());
    }
}