package by.btslogistics.beltamozhservisproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
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
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

/**    Путь к XSD схеме документа относительно программы */
    @Column(name = "schema_location")
    private String schemaLocation;

/**    Корневой XML тэг */
    @Column(name = "root_element")
    private String rootElement;

/**    Версия XSD схемы */
    @Column(name = "schema_version")
    private String schemaVersion;

/**    Код типа документа, к которому относится схема */
    @Column(name = "schema_name")
    private String schemaName;

/**    Связь с таблицей "TAG_DOCUMENT" */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "document", cascade = CascadeType.ALL)
    private Set<Tag> tags;
}
