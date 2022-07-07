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
@Table(name = "STRUCTURE_DOCUMENT")
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "schema_location")
    private String schemaLocation;

    @Column(name = "root_element")
    private String rootElement;

    @Column(name = "schema_version")
    private String schemaVersion;

    @Column(name = "schema_name")
    private String schemaName;
}
