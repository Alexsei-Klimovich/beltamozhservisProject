package by.btslogistics.beltamozhservisproject.service;

import by.btslogistics.beltamozhservisproject.exception.NotFoundException;
import by.btslogistics.beltamozhservisproject.model.Tag;
import by.btslogistics.beltamozhservisproject.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;
    public void deleteTagById(Long tagId){
        tagRepository.deleteById(tagId);
    }
    public Tag getTagById(Long tagId){
        return tagRepository.findById(tagId).orElseThrow(NotFoundException::new);
    }
    @Transactional
    public void saveTag(Tag tag){
        tagRepository.save(tag);
    }
    @Transactional
    public void updateTag(Tag tag){
        tagRepository.save(tag);
    }

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    public Tag getTagByNodePath(String nodePath){
        return tagRepository.getTagByNodePath(nodePath);
    }

    public Tag getTagByParentPath(String parentPath){
        return tagRepository.getTagByParentPath(parentPath);
    }

}
