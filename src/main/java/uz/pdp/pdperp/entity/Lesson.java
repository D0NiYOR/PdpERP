package uz.pdp.pdperp.entity;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.pdperp.entity.enums.LessonStatus;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Lesson extends BaseEntity{
  //  group

    private Integer module;

    @ManyToOne
    private Group group;

    private Integer lessonNumber;

    private List<Attendance> attendances;

    private LessonStatus status;
}
