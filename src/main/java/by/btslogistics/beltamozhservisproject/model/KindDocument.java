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
@Table(name = "kind_document")
@Entity
public class KindDocument {
    @Id
    @SequenceGenerator(name = "seq_kind_document",
            sequenceName = "seq_kind_document",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kind_document")
    @Column(name = "id")
    private Long id;

    @Column(name = "code_eng")
    private String codeEng;

    @Column(name = "code_rus")
    private String codeRus;

    @Column(name = "description")
    private String description;

    @Column(name = "date_activate")
    private LocalDateTime activateDateDocument;

    @Column(name = "date_deactivate")
    private LocalDateTime deactivateDateDocument;

    @OneToOne(mappedBy = "kindDocument")
    private KindStructure kindStructure;
}
