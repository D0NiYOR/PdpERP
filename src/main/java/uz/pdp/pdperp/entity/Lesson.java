package uz.pdp.pdperp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import uz.pdp.pdperp.entity.enums.LessonStatus;

import java.util.List;
@Entity(name = "lesson")
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

    @OneToMany
    private List<Attendance> attendances;

    private LessonStatus status;
}
