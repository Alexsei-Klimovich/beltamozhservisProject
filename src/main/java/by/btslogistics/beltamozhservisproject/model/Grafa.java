package by.btslogistics.beltamozhservisproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "flk_grafa")
@Entity
public class Grafa {

    @Id
    @SequenceGenerator(name = "seq_flk_grafa",
            sequenceName = "seq_flk_grafa",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="seq_flk_grafa" )
    @Column(name = "id")
    private Long id;

    /** Код типа документа, к которму относиться схема*/
    @Column(name = "id_form")
    private Long formId;

    /** Описание графы*/
    @Column(name = "name_grafa")
    private String nameGrafa ;

    /** Описание поля*/
    @Column(name = "name_pole")
    private String namePole;

    /** Путь к полю*/
    @Column(name = "path_xml")
    private String pathXML;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grafa", cascade = CascadeType.ALL)
    private List<Check> checks = new ArrayList<>();

    public void addCheck(Check check){
        checks.add(check);
        check.setGrafa(this);
        check.setGrafaId(this.id);
    }

}
