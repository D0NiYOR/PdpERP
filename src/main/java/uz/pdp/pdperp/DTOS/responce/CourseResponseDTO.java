package uz.pdp.pdperp.DTOS.responce;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDTO {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer moduleCount;
}
