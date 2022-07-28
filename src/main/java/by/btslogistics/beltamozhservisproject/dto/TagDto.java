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
public class TagDto {
    private Long toStrdocId;
    private String nodeName;
    private String nodePath;
    private String parentName;
    private String parentPath;
    private String pattern;
    private Long patternId;
}
