package by.btslogistics.beltamozhservisproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "id_form")
    private Long formId;

    @Column(name = "name_grafa")
    private String nameGrafa;

    @Column(name = "name_pole")
    private String namePole;

    @Column(name = "path_xml")
    private String pathXML;

}
