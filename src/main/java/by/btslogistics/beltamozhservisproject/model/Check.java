package by.btslogistics.beltamozhservisproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flk_checks")
@Entity
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_grafa")
    private Long grafaId;

    @Column(name = "to_tag_doc_id")
    private Long toTagDocId;

    @Column(name = "code_check")
    private String checkCode;

    @Column(name = "description_check")
    private String checkDescription;

    @Column(name = "description_error")
    private String errorDescription;

    @Column(name = "d_on")
    private LocalDateTime startCheckTime;

    @Column(name = "d_off")
    private LocalDateTime endCheckTime;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_grafa", nullable = false)
    private Grafa grafa;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "to_tag_doc_id", nullable = false)
    private Tag tag;


}
