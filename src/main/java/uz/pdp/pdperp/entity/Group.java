package uz.pdp.pdperp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import uz.pdp.pdperp.entity.enums.GroupStatus;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Group extends BaseEntity {

    @Column(unique = true , nullable = false)
    private String groupName;

    @Max(value = 24,message = "24 tadan ko'p bola xonaga sig'maydi")
    @Min(value = 10,message = "10 ta bolaga dars o'tish qoplamaydi")
    private Integer studentCount;

    @OneToMany
    private Course course;

    @OneToMany
    private MentorEntity mentorEntity;

    private GroupStatus status;

   //private Integer duration modulcount


}
