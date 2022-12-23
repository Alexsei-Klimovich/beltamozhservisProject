package by.btslogistics.beltamozhservisproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "checks_not_exists")
@Entity
public class ChecksNotExistsModel {

    @Id
    @SequenceGenerator(name = "seq_not_exist",
            sequenceName = "seq_not_exist",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_not_exist")
    @Column(name = "id")
    private Long id;

    @Column(name = "not_in_java")
    private String notInJava;

    @Column(name = "not_in_directory")
    private String notInDirectory;
}
