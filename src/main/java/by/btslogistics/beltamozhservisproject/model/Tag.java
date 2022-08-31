package by.btslogistics.beltamozhservisproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag_document")
@Entity
public class Tag {

    @Id
    @SequenceGenerator(name = "seq_tag_document",
            sequenceName = "seq_tag_document",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tag_document")
    @Column(name = "id")
    private Long id;

    /** Уникальный идентификатор структуры*/
    @Column(name = "to_strdoc_id", insertable = false, updatable = false)
    private Long toStrdocId;

    /** Описание поля*/
    @Column(name = "node_name")
    private String nodeName;

    /** Путь к полю*/
    @Column(name = "node_path")
    private String nodePath ;

    /** Описание поля родительского тега*/
    @Column(name = "parent_name")
    private String parentName ;

    /** Путь к родительскому полю*/
    @Column(name = "parent_path")
    private String parentPath;

    /** Паттерн*/
    @Column(name = "pattern")
    private String pattern;

    /** Id родительского поля*/
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "multiplicity")
    private String multiplicity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tag", cascade = CascadeType.ALL)
    private List<Check> checks = new ArrayList<>();

    public void addCheck(Check check){
        checks.add(check);
        check.setTag(this);
    }

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "to_strdoc_id", nullable = false)
    private StructureDocument structureDocument = new StructureDocument();
}
