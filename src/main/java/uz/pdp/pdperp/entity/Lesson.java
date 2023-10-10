package uz.pdp.pdperp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.pdperp.entity.enums.LessonStatus;

@Entity(name = "lesson")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Lesson extends BaseEntity {

    private Integer module;

    @ManyToOne
    private Group group;

    private Integer lessonNumber;

    //    @OneToMany
//    private List<Attendance> attendances;
    private LessonStatus status;
}
