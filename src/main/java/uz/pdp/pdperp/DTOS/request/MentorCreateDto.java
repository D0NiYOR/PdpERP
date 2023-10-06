package uz.pdp.pdperp.DTOS.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MentorCreateDto {
    private String languageName;
    private Double experience;
    private Double salary;
}
