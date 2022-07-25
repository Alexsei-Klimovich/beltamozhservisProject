package by.btslogistics.beltamozhservisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KindDocumentDto {
    private Long id;
    private Long code_eng;
    private Long code_rus;
    private String description;
    private LocalDateTime date_activate;
    private LocalDateTime date_deactivate;
}
