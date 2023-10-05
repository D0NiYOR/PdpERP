package uz.pdp.pdperp.entity;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MentorEntity {

    @OneToOne
    private UserEntity userEntity;
    private Double experience;
    private Double salary;
}
