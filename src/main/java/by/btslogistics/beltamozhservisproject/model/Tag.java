package by.btslogistics.beltamozhservisproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag_document")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "to_strdoc_id")
    private Long toStrdocId;

    @Column(name = "node_name")
    private Long nodeName;

    @Column(name = "node_path")
    private String nodePath;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "parent_path")
    private String parentPath;

    @Column(name = "pattern")
    private String pattern;

    @Column(name = "parent_id")
    private Long parentId;
}
