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
public class GrafaDto {
    private Long formId;
    private String nameGrafa;
    private String namePole;
    private String pathXML;
}
