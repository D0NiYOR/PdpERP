package uz.pdp.pdperp.DTOS.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.pdperp.entity.enums.GroupStatus;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GroupCreateDto {
    private String groupName;
    private UUID cursId;
    private UUID mentorId;
    private Integer studentCount;
    private GroupStatus status;

}
