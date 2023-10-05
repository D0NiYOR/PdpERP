package uz.pdp.pdperp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentEntity extends BaseEntity{
    @OneToOne
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Group group;

}
