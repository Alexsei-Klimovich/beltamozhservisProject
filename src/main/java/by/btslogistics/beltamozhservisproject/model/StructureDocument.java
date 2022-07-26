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
@Table(name = "STRUCTURE_DOCUMENT")
@Entity
public class StructureDocument {

    @Id
    @SequenceGenerator(name = "seq_structure_document",
            sequenceName = "seq_structure_document",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_structure_document")
    @Column(name = "id")
    private Long id;

    /**    Путь к XSD схеме документа относительно программы */
    @Column(name = "schema_location")
    private String schemaLocation;

    /**    Корневой XML тэг */
    @Column(name = "root_element")
    private String rootElement ;

    /**    Версия XSD схемы */
    @Column(name = "schema_version")
    private String schemaVersion;

    /**    Код типа документа, к которому относится схема */
    @Column(name = "schema_name")
    private String schemaName;

    /**    Связь с таблицей "TAG_DOCUMENT" */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "structureDocument", cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    @OneToOne(mappedBy = "structureDocument")
    private KindStructure kindStructure;

    public void addTag(Tag tag){
        tags.add(tag);
        tag.setStructureDocument(this);
    }
}
