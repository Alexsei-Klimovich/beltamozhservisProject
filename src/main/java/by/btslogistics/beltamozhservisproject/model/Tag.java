package by.btslogistics.beltamozhservisproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.print.Doc;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TAG_DOCUMENT")
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

    @Column(name = "pattern_id")
    private Long patternId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tag", cascade = CascadeType.ALL)
    private Set<Check> checks;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "to_strdoc_id", nullable = false)
    private Document document;
}
