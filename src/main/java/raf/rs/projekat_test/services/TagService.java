package raf.rs.projekat_test.services;

import raf.rs.projekat_test.entities.Tag;
import raf.rs.projekat_test.repositories.tag.TagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {

    @Inject
    private TagRepository tagRepository;

    public Tag addTag(Tag tag) {
        return this.tagRepository.addTag(tag);
    }

    public List<Tag> allTags() {
        return this.tagRepository.allTags();
    }

    public Tag findTag(Integer id) {
        return this.tagRepository.findTag(id);
    }

    public void deleteTag(Integer id) {
        this.tagRepository.deleteTag(id);
    }

}
