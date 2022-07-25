package by.btslogistics.beltamozhservisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KindStructure {
    private Long id;
    private Long to_kind_id;
    private Long to_struct_doc_id;
    private LocalDateTime date_activate;
    private LocalDateTime date_deactivate;
}
