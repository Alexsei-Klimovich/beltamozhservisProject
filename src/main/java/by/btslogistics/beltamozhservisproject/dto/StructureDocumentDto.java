package by.btslogistics.beltamozhservisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author Alexsei
 * @author Yaroslav
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureDocumentDto {
    private String schemaLocation;
    private String rootElement;
    private String schemaVersion;
    private String schemaName;
}
