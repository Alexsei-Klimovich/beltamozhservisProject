package by.btslogistics.beltamozhservisproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kind_m2m_structure")
@Entity
public class KindStructure {
    @Id
    @SequenceGenerator(name = "seq_kind_m2m_structure",
            sequenceName = "seq_kind_m2m_structure",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kind_m2m_structure")
    @Column(name = "id")
    private Long id;

    @Column(name = "to_kind_id", insertable = false, updatable = false)
    private Long toKindId;

    @Column(name = "to_struct_doc_id", insertable = false, updatable = false)
    private Long toStructDocId;

    @Column(name = "date_activate")
    private LocalDateTime activateDateDStructure;

    @Column(name = "date_deactivate")
    private LocalDateTime deactivateDateStructure;

    @OneToOne(mappedBy = "kindStructure")
    private TypeControl typeControl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_kind_id", referencedColumnName = "id")
    private KindDocument kindDocument;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "to_struct_doc_id", referencedColumnName = "id")
//    private StructureDocument structureDocument;
}
