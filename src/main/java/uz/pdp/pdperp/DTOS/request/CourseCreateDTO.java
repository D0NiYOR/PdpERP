package uz.pdp.pdperp.DTOS.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CourseCreateDTO {
    @NotBlank(message = "course name must not be blank")
    private String name;
    @NotBlank(message = "start date must not be blank")
    private LocalDateTime startDate;
    @NotBlank(message = "end date must not be blank")
    private LocalDateTime endDate;
    @NotBlank(message = "module count must not be blank")
    private Integer moduleCount;
}
