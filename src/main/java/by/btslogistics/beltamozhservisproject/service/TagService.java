package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.model.Tag;
import by.btslogistics.beltamozhservisproject.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public void deleteTagById(Long tagId){
        tagRepository.deleteById(tagId);
    }


    public Tag getTagById(Long tagId){
        return tagRepository.getById(tagId);
    }

    public void saveTag(Tag tag){
        tagRepository.save(tag);
    }

    public void updateTag(Tag tag){
        tagRepository.save(tag);
    }

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

}
