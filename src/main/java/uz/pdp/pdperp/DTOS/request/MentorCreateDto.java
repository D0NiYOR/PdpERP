package uz.pdp.pdperp.DTOS.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.pdperp.entity.enums.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MentorCreateDto {
    private Double experience;
    private Double salary;
}
