package by.btslogistics.beltamozhservisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckDto {
    private Long grafaId;
    private Long toTagDocId;
    private String checkCode;
    private String checkDescription;
    private String errorDescription;
    private LocalDateTime startCheckTime;
    private LocalDateTime endCheckTime;

}
