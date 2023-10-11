package uz.pdp.pdperp.DTOS.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import uz.pdp.pdperp.entity.enums.GroupStatus;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UpdateGroupDto {

    private String groupName;
    private UUID cursId;
    private UUID mentorId;
    private Integer studentCount;
    private GroupStatus status;
}
