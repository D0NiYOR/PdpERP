package uz.pdp.pdperp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "mentor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MentorEntity extends BaseEntity {

    @OneToOne
    private UserEntity userEntity;
    private String languageName;
    private Double experience;
    private Double salary;
    private boolean delete;
}
