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
@Table(name = "flk_grafa")
@Entity
public class Grafa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /** Код типа документа, к которму относиться схема*/
    @Column(name = "id_form")
    private Long formId;

    /** Описание графы*/
    @Column(name = "name_grafa")
    private String nameGrafa;

    /** Описание поля*/
    @Column(name = "name_pole")
    private String namePole;

    /** Путь к полю*/
    @Column(name = "path_xml")
    private String pathXML;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "grafa", cascade = CascadeType.ALL)
    private Set<Check> checks;

}
