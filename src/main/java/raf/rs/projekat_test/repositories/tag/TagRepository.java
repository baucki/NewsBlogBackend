package raf.rs.projekat_test.repositories.tag;

import raf.rs.projekat_test.entities.Tag;

import java.util.List;

public interface TagRepository {

    public Tag addTag(Tag tag);

    public List<Tag> allTags();

    public Tag findTag(Integer id);

    public void deleteTag(Integer id);

}
