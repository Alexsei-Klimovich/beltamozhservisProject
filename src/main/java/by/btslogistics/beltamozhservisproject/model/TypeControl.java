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
@Table(name = "flk_type_control")
@Entity
public class TypeControl {
    @Id
    @SequenceGenerator(name = "seq_flk_type_control",
            sequenceName = "seq_flk_type_control",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_flk_type_control")
    @Column(name = "id")
    private Long id;

    @Column(name = "to_kind_m2m_structure_id", insertable = false, updatable = false)
    private Long toKindM2MStructureId;

    @Column(name = "name_type")
    private String nameType;

    @Column(name = "description")
    private String description;

    @Column(name = "d_on")
    private LocalDateTime startCheckTime;

    @Column(name = "d_off")
    private LocalDateTime endCheckTime;

    @Column(name = "is_active")
    private String isActive;

    @Column(name = "default_control")
    private Long defaultControl;

    @Column(name = "date_create")
    private LocalDateTime createDate;

    @Column(name = "date_update")
    private LocalDateTime updateDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_kind_m2m_structure_id", referencedColumnName = "id")
    private KindStructure kindStructure;
}
