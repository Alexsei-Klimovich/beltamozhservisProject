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
@Table(name = "checks_from_excel")
@Entity
public class ChecksFromExcelModel {

    @Id
    @SequenceGenerator(name = "seq_compare_checks",
            sequenceName = "seq_compare_checks",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_compare_checks")
    @Column(name = "id")
    private Long id;

    @Column(name = "code_check", nullable = false)
    private String codeCheck;
}
