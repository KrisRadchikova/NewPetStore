package by.tms.petstore.resource;

import by.tms.petstore.model.Tag;
import by.tms.petstore.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/tag")
public class TagResource {

    private TagService tagService;

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Tag>> getAllTags() {
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{tagId}")
    public Optional<Tag> findTagById(@PathVariable long tagId) {
        return tagService.findTagById(tagId);
    }

    @PostMapping(path = "/add")
    public Tag addNewTag(@RequestBody Tag tag) {
        return tagService.addNewTag(tag);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteTag(@PathVariable long id) {
        tagService.deleteTagById(id);
    }


    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
}
