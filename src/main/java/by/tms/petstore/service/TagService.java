package by.tms.petstore.service;

import by.tms.petstore.model.Tag;
import by.tms.petstore.repository.TagRepository;
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
public class TagService {

    private TagRepository tagRepository;

    //get all tags
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    //add new tag
    public Tag addNewTag(Tag tag) {
        ExampleMatcher addNewTagMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("name", ignoreCase());
        Example<Tag> tagExample = Example.of(tag, addNewTagMatcher);
        if (!tagRepository.exists(tagExample)) {
            return tagRepository.save(tag);
        }
        throw new ExistsException("This tag exists");
    }

    //find tag by id
    public Optional<Tag> findTagById(long id) {
        if (tagRepository.findById(id).isPresent()) {
            return tagRepository.findById(id);
        }
        throw new NotFoundException("Tag with id " + id + " not found");
    }

    //delete tag
    public void deleteTagById(long id) {
        if (tagRepository.findById(id).isPresent()) {
            tagRepository.deleteById(id);
        }
        throw new NotFoundException("Tag with id " + id + " not found");
    }

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
}
