package uz.pdp.pdperp.entity;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attendance extends BaseEntity {
    @OneToOne
   private Lesson lesson ;
    @OneToOne
   private StudentEntity student ;

    private boolean status;

}
