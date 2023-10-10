package uz.pdp.pdperp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentEntity extends BaseEntity {

    boolean isActive;
    @OneToOne
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Group group;

}
