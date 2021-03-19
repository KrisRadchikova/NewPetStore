package by.tms.petstore.service;

import by.tms.petstore.model.Tag;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") //resources
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TagServiceTest {

    private List<String> TAGS = new ArrayList<>();

    public TagServiceTest() {
        TAGS.add("sphinx");
        TAGS.add("abyssinian");
    }

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
        List<String> saved = TAGS.stream()
                .map(Tag::new)
                .map(tagService::addNewTag)
                .map(Tag::getName)
                .collect(Collectors.toList());
        assertEquals(saved, TAGS);
    }

    @Test
    @Order(4)
    void getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        assertEquals(TAGS.size(), tags.size());
    }

    @Test
    @Order(3)
    void findTagById() {
        Optional<Tag> tag = tagService.findTagById(1);
        assertTrue(tag.isPresent());
        assertEquals("sphinx", tag.get().getName());
    }

    @Test
    @Order(5)
    void deleteTagById() {
        String tag = tagService.deleteTagById(1);
        assertNull(tag);
        /*if(tagService.findTagById(1).isPresent()) {
            fail("Test failed");
        }
        assertNull(tagService.findTagById(1));*/
    }
}
