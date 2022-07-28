package by.btslogistics.beltamozhservisproject.controller;

import by.btslogistics.beltamozhservisproject.dto.TagDto;
import by.btslogistics.beltamozhservisproject.mapper.TagMapper;
import by.btslogistics.beltamozhservisproject.model.Tag;
import by.btslogistics.beltamozhservisproject.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @Autowired
    TagMapper tagMapper;

    @PostMapping("/createTag")
    public ResponseEntity<String> createTag(@ModelAttribute("tag") Tag tag){
        tagService.saveTag(tag);
        return new ResponseEntity<>("Saved",HttpStatus.OK);
    }

    @GetMapping("/getTag")
    public ResponseEntity<TagDto> getTagById(@RequestParam("id") String tagId) {
        Tag tag = tagService.getTagById(Long.parseLong(tagId));
        TagDto tagDto = tagMapper.toDto(tag);
        if (tag != null) {
            return new ResponseEntity<>(tagDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllTags")
    public ResponseEntity<List<TagDto>> getAllTags(){
        List<Tag> tagList = tagService.getAllTags();
        List<TagDto> tagDtoList = new ArrayList<>();
        for(var tag : tagList){
            tagDtoList.add(tagMapper.toDto(tag));
        }
        return new ResponseEntity<>(tagDtoList,HttpStatus.OK);
    }

    @DeleteMapping("/deleteTag")
    public ResponseEntity<String> deleteTag(@RequestParam("id") String tagId) {
        tagService.deleteTagById(Long.parseLong(tagId));
        return new ResponseEntity<>("Tag deleted", HttpStatus.OK);
    }

}
