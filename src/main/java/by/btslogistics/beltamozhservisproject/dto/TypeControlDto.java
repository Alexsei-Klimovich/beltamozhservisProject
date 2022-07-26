package by.btslogistics.beltamozhservisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeControlDto {
    private Long id;
    private String name_type;
    private LocalDateTime d_on;
    private LocalDateTime d_off;
    private String is_active;
    private Long default_control;
    private LocalDateTime date_create;
    private LocalDateTime date_update;
}
