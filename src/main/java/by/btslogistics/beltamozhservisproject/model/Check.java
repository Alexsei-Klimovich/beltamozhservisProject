package by.btslogistics.beltamozhservisproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
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
@Table(name = "flk_checks")
@Entity
public class Check {

    @Id
    @SequenceGenerator(name = "seq_flk_checks",
            sequenceName = "seq_flk_checks",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_flk_checks")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_grafa", insertable = false, updatable = false)
    private Long grafaId;

    @Column(name = "to_tag_doc_id", insertable = false, updatable = false)
    private Long toTagDocId;

/**    Код проверки */
    @Column(name = "code_check")
    private String checkCode ;

/**    Описание проверки */
    @Column(name = "description_check")
    private String checkDescription;

/**    Описание ошибки при непрохождение проверки */
    @Column(name = "description_error")
    private String errorDescription;

/**   Дата начала работы проверки */
    @Column(name = "d_on")
    private LocalDateTime startCheckTime;

/**    Дата окончания работы проверки */
    @Column(name = "d_off")
    private LocalDateTime endCheckTime;

/**    Связь с таблицей "FLK_GRAFA" */
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_grafa", nullable = false)
    private Grafa grafa = new Grafa();

/**   Связь с таблицей "TAG_DOCUMENT" */
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "to_tag_doc_id", nullable = false)
    private Tag tag = new Tag();


}
